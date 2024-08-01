package com.dev.encurta_ai.service;

import com.dev.encurta_ai.dto.LinkResponse;
import com.dev.encurta_ai.model.Link;
import com.dev.encurta_ai.repository.LinkRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkService {

    private final String URL_REDIRECT = "http://localhost:8080/r/";

    @Autowired
    private LinkRepository linkRepository;

    private String createUrlShort(){
        return RandomStringUtils.randomAlphabetic(5, 10);
    }

    @Transactional
    public LinkResponse createLink(String urlOriginal){
        Link link = new Link();
        link.setUrlLong(urlOriginal);
        link.setUrlShort(createUrlShort());
        link.setUrlQrCode("Indisponível momentaneamente");
        link.setCreatedAt(LocalDateTime.now());
        return new LinkResponse(linkRepository.save(link), URL_REDIRECT + link.getUrlShort());
    }

    public String recoverUrlOriginal(String urlShort){
        String urlOriginal = linkRepository.findByUrlShort(urlShort);
        return urlOriginal;
    }
}
