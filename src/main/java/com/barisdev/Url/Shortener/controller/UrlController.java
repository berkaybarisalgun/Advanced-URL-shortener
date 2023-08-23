package com.barisdev.Url.Shortener.controller;

import com.barisdev.Url.Shortener.dto.DtoToEntityConverter;
import com.barisdev.Url.Shortener.dto.UrlDto;
import com.barisdev.Url.Shortener.entity.Url;
import com.barisdev.Url.Shortener.request.UrlRequest;
import com.barisdev.Url.Shortener.request.UrlRequestToUrlConverter;
import com.barisdev.Url.Shortener.service.UrlService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UrlController {

    private final UrlService service;

    private final DtoToEntityConverter dtoToEntityConverter;

    private final UrlRequestToUrlConverter urlRequestToUrlConverter;

    public UrlController(UrlService service, DtoToEntityConverter dtoToEntityConverter, UrlRequestToUrlConverter urlRequestToUrlConverter) {
        this.service = service;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.urlRequestToUrlConverter = urlRequestToUrlConverter;
    }


    @GetMapping
    public ResponseEntity<List<UrlDto>> getAllUrls() {
        return new ResponseEntity<List<UrlDto>>(
                dtoToEntityConverter.convertAllToDto(service.getAllUrl()), HttpStatus.OK

        );
    }

    @GetMapping("/get/{ref}")
    public ResponseEntity<UrlDto> getUrlByCode(@Valid @NotEmpty @PathVariable String ref) {
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
    public ResponseEntity<?> create(@Valid @RequestBody UrlRequest urlRequest) {
        Url url = urlRequestToUrlConverter.urlRequestToUrl(urlRequest);
        return new ResponseEntity<UrlDto>(
                dtoToEntityConverter.convertToDto(service.create(url)), HttpStatus.CREATED
        );
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Url deleted successfully");
    }

    @PutMapping("/update/{ref}") // Specify the URL for updating
    public ResponseEntity<?> updateUrl(@Valid @RequestBody UrlRequest urlRequest, @PathVariable String ref) {

        Url url = urlRequestToUrlConverter.urlRequestToUrl(urlRequest);
        url.setRef(ref); // Set the ref from the URL path
        Url updatedUrl = service.update(url);

        return new ResponseEntity<UrlDto>(
                dtoToEntityConverter.convertToDto(updatedUrl), HttpStatus.OK
        );
    }


}
