package com.dev.encurta_ai.service;

import com.dev.encurta_ai.dto.LinkResponse;
import com.dev.encurta_ai.infra.exception.NotFoundException;
import com.dev.encurta_ai.model.Link;
import com.dev.encurta_ai.repository.LinkRepository;
import com.google.zxing.WriterException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private LinkLogService linkLogService;
    @Autowired
    private QrCodeService qrCodeService;

    private String createUrlShort(){
        return RandomStringUtils.randomAlphabetic(5, 10);
    }

    @Transactional
    public LinkResponse createLink(String urlOriginal, HttpServletRequest request){
        Link link = new Link();
        link.setUrlLong(urlOriginal);
        link.setUrlShort(createUrlShort());
        String urlRedirect = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/r/" + link.getUrlShort();

        try {
            String qrCodeBase64 = qrCodeService.generateQRCode(urlRedirect);
            link.setUrlQrCode(qrCodeBase64);
        } catch (WriterException | IOException e) {
            link.setUrlQrCode("Error generating QR Code");
        }
        return new LinkResponse(linkRepository.save(link), urlRedirect);
    }

    public String recoverUrlOriginal(String urlShort, HttpServletRequest request){
        String urlOriginal = linkRepository.findByUrlShort(urlShort);
        if(urlOriginal == null){
            throw new NotFoundException("Link not found");
        }
        linkLogService.logClick(urlShort, request);
        return urlOriginal;
    }
}
