package com.barisdev.Url.Shortener.controller;

import com.barisdev.Url.Shortener.dto.DtoToEntityConverter;
import com.barisdev.Url.Shortener.dto.UrlDto;
import com.barisdev.Url.Shortener.entity.Url;
import com.barisdev.Url.Shortener.request.UrlRequestToUrlConverter;
import com.barisdev.Url.Shortener.service.UrlService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
public class UrlController {

    private final UrlService service;

    private final DtoToEntityConverter dtoToEntityConverter;

    public UrlController(UrlService service, DtoToEntityConverter dtoToEntityConverter, UrlRequestToUrlConverter urlRequestToUrlConverter) {
        this.service = service;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.urlRequestToUrlConverter = urlRequestToUrlConverter;
    }

    private final UrlRequestToUrlConverter urlRequestToUrlConverter;


    @GetMapping
    public ResponseEntity<List<UrlDto>> getAllUrls(){
        return new ResponseEntity<List<UrlDto>>(
                dtoToEntityConverter.convertAllToDto(service.getAllUrl()),HttpStatus.OK

        );
    }

    @GetMapping("get/{ref}")
    public ResponseEntity getUrl(@Valid @NotEmpty @PathVariable String ref) throws URISyntaxException {

        Url url=service.getUrlByref(ref);

        URI uri = new URI(url.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<UrlDto>(
                httpHeaders, HttpStatus.SEE_OTHER
        );

    }

}
