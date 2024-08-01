package com.dev.encurta_ai.dto;

import com.dev.encurta_ai.model.Link;

import java.time.LocalDateTime;

public record LinkResponse(
        Long id,
        String urlLong,
        String urlShort,
        String urlQrCode,
        LocalDateTime createdAt
) {


    public LinkResponse(Link link, String urlShort) {
        this(link.getId(),
                link.getUrlLong(),
                urlShort,
                link.getUrlQrCode(),
                link.getCreatedAt());
    }
}
