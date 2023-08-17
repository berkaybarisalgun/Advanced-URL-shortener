package com.barisdev.Url.Shortener.request;

import com.barisdev.Url.Shortener.entity.Url;
import org.springframework.stereotype.Component;

@Component
public class UrlRequestToUrlConverter {

    public Url urlRequestToUrl(UrlRequest urlRequest){
        Url.builder()
                .url(urlRequest.getUrl())
                .key(urlRequest.getkey())
                .build();
    }
}
