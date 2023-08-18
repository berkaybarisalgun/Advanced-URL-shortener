package com.barisdev.Url.Shortener.service;


import com.barisdev.Url.Shortener.entity.Url;
import com.barisdev.Url.Shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {
    private final UrlRepository repository;


    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public List<Url> getAllUrl(){
        return repository.findAll();
    }
    public Url getUrlByCode(String code){
        return repository.findByCode(code).orElseThrow(
                ()->new RuntimeException("not found");
        )
    }

}
