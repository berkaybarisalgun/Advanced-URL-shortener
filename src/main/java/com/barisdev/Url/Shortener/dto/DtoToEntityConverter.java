package com.barisdev.Url.Shortener.dto;

import com.barisdev.Url.Shortener.entity.Url;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoToEntityConverter {

    public UrlDto convertToDto(Url url) {
        return UrlDto.builder()
                .id(url.getId())
                .url(url.getUrl())
                .ref(url.getRef())
                .expirationDate(url.getExpirationDate())
                .build();
    }

    public List<UrlDto> convertAllToDto(List<Url> url) {
        return url.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
