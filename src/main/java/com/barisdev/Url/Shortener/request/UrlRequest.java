package com.barisdev.Url.Shortener.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequest {

    @NotNull
    private String url;

    private String ref;

}
