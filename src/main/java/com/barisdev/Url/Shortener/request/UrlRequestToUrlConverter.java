package com.barisdev.Url.Shortener.request;

import com.barisdev.Url.Shortener.entity.Url;
import org.springframework.stereotype.Component;

@Component
public class UrlRequestToUrlConverter {

    public Url urlRequestToUrl(UrlRequest urlRequest){
       return  Url.builder()
                .url(urlRequest.getUrl())
                .ref(urlRequest.getRef())
                .build();
    }
}
