package com.barisdev.Url.Shortener.controller;

import com.barisdev.Url.Shortener.dto.DtoToEntityConverter;
import com.barisdev.Url.Shortener.request.UrlRequestToUrlConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UrlController {

    //private final DtoToEntityConverter;

    //private final UrlRequestToUrlConverter;


    @GetMapping
    public List<ResponseEntity> getAllUrls(){

    }

    @GetMapping("get/{key}")
    public ResponseEntity

}
