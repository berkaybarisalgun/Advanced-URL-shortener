package com.barisdev.Url.Shortener.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {

    private Long id;

    private String url;

    private String ref;

    private LocalDate expirationDate;

    private String qrCodePath;

}
