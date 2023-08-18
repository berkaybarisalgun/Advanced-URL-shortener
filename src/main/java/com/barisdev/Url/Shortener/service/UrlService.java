package com.barisdev.Url.Shortener.service;


import com.barisdev.Url.Shortener.entity.Url;
import com.barisdev.Url.Shortener.repository.UrlRepository;
import com.barisdev.Url.Shortener.util.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {
    private UrlRepository repository;

    private RandomStringGenerator randomStringGenerator=new RandomStringGenerator();


    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public List<Url> getAllUrl(){
        return repository.findAll();
    }
    public Url getUrlByref(String ref){
        return repository.findByref(ref).orElseThrow(
                ()->new RuntimeException("not found")
        );
    }

    public Url create(Url url){
        if(url.getRef()==null || url.getRef().isEmpty()){
            url.setRef(generateref());
        }
        else if(repository.findByref(url.getRef()).isPresent()){
            throw new RuntimeException("same value already exist");
        }
        return repository.save(url);
    }

    private String generateref() {
        String ref;

        do{
            ref= randomStringGenerator.generateRandomString();
        }while(repository.findByref(ref).isPresent());

        return ref;


    }

}
