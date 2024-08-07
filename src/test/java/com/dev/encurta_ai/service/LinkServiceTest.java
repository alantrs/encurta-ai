package com.dev.encurta_ai.service;

import com.dev.encurta_ai.dto.LinkResponse;
import com.dev.encurta_ai.infra.exception.NotFoundException;
import com.dev.encurta_ai.model.Link;
import com.dev.encurta_ai.repository.LinkRepository;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class LinkServiceTest {

    @InjectMocks
    private LinkService linkService;

    @Mock
    private LinkRepository linkRepository;

    @Mock
    private QrCodeService qrCodeService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private LinkLogService linkLogService;

    @Test
    @DisplayName("Should create link")
    void createLinkSuccess() throws IOException, WriterException {
        Link link = new Link(1L, "Url long", "Url short", "QR Code", LocalDateTime.now());
        Mockito.when(qrCodeService.generateQRCode(Mockito.anyString())).thenReturn("QR Code");
        Mockito.when(linkRepository.save(Mockito.any(Link.class))).thenReturn(link);

        LinkResponse linkSaved = linkService.createLink("Url long");

        assertThat(linkSaved).isNotNull();
        assertThat(linkSaved.id()).isEqualTo(1L);
        assertThat(linkSaved.urlLong()).hasSizeBetween(5, 10);

        Mockito.verify(linkRepository, Mockito.times(1)).save(Mockito.any(Link.class));
    }

    @Test
    @DisplayName("Should return Not found Exception")
    void recoverUrlOriginalNotFound() {
        Mockito.when(linkRepository.findByUrlShort(Mockito.anyString())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> linkService.recoverUrlOriginal("Url short", request));
    }

    @Test
    @DisplayName("Should return original URL")
    void recoverUrlOriginalSuccess() {
        String originalUrl = "Url long";
        Mockito.when(linkRepository.findByUrlShort(Mockito.anyString())).thenReturn(originalUrl);

        String result = linkService.recoverUrlOriginal("Url short", request);

        assertThat(result).isEqualTo(originalUrl);

        Mockito.verify(linkLogService, Mockito.times(1)).logClick(Mockito.anyString(), Mockito.any(HttpServletRequest.class));
    }
}