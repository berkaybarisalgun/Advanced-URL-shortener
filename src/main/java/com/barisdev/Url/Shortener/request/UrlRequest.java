package com.barisdev.Url.Shortener.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequest {

    @NotNull
    private String url;

    private String ref;

    private LocalDate expirationDate;

    public void setExpirationDateAsString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.expirationDate = LocalDate.parse(dateString, formatter);
    }

}
