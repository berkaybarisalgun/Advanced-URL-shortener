package com.barisdev.Url.Shortener.controller;

import com.barisdev.Url.Shortener.dto.DtoToEntityConverter;
import com.barisdev.Url.Shortener.dto.UrlDto;
import com.barisdev.Url.Shortener.entity.Url;
import com.barisdev.Url.Shortener.request.UrlRequest;
import com.barisdev.Url.Shortener.request.UrlRequestToUrlConverter;
import com.barisdev.Url.Shortener.service.UrlService;
import com.barisdev.Url.Shortener.util.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
public class UrlController {

    private final UrlService service;

    private final DtoToEntityConverter dtoToEntityConverter;

    private final UrlRequestToUrlConverter urlRequestToUrlConverter;

    private final QRCodeGenerator qrCodeGenerator=new QRCodeGenerator();

    public UrlController(UrlService service, DtoToEntityConverter dtoToEntityConverter, UrlRequestToUrlConverter urlRequestToUrlConverter) {
        this.service = service;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.urlRequestToUrlConverter = urlRequestToUrlConverter;
    }


    @GetMapping
    public ResponseEntity<List<UrlDto>> getAllUrls() throws IOException, WriterException {
        List<Url> urls=service.getAllUrl();
        if(urls.size() != 0){
            for(Url url:urls){
                System.out.println("generated");
                qrCodeGenerator.generateQRCode(url);
            }
        }
        return new ResponseEntity<List<UrlDto>>(
                dtoToEntityConverter.convertAllToDto(service.getAllUrl()), HttpStatus.OK

        );
    }

    @GetMapping("/get/{ref}")
    public ResponseEntity<UrlDto> getUrlByCode(@Valid @NotEmpty @PathVariable String ref) throws IOException, WriterException {
        ref = ref.toUpperCase();
        return new ResponseEntity<UrlDto>(
                dtoToEntityConverter.convertToDto(service.getUrlByref(ref)), HttpStatus.OK
        );
    }

    @GetMapping("/{ref}")
    public ResponseEntity<UrlDto> redirect(@Valid @NotEmpty @PathVariable String ref) throws URISyntaxException {

        Url url = service.getUrlByref(ref);

        URI uri = new URI(url.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<UrlDto>(
                httpHeaders, HttpStatus.SEE_OTHER
        );
    }


    @PostMapping("/create/")
    public ResponseEntity<?> create(@Valid @RequestBody UrlRequest urlRequest) throws Exception {
        Url url = urlRequestToUrlConverter.urlRequestToUrl(urlRequest);
        return new ResponseEntity<UrlDto>(
                dtoToEntityConverter.convertToDto(service.create(url)), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Url deleted successfully");
    }

    @PutMapping("/update/{ref}") // Specify the URL for updating
    public ResponseEntity<?> updateUrl(@Valid @RequestBody UrlRequest urlRequest, @PathVariable String ref) throws IOException, WriterException {

        Url url = urlRequestToUrlConverter.urlRequestToUrl(urlRequest);
        url.setRef(ref); // Set the ref from the URL path
        Url updatedUrl = service.update(url);

        return new ResponseEntity<UrlDto>(
                dtoToEntityConverter.convertToDto(updatedUrl), HttpStatus.OK
        );
    }




}
