package com.dev.encurta_ai.service;

import com.dev.encurta_ai.dto.LinkResponse;
import com.dev.encurta_ai.infra.exception.NotFoundException;
import com.dev.encurta_ai.model.Link;
import com.dev.encurta_ai.repository.LinkRepository;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
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

    private Link link;

    @BeforeEach
    void setUp(){
        link = new Link(1L, "Url long", "Url short", "QR Code", LocalDateTime.now());
    }


    @Test
    @DisplayName("Should create link")
    void createLinkSuccess() throws IOException, WriterException {
        when(qrCodeService.generateQRCode(anyString())).thenReturn("QR Code");
        when(linkRepository.save(any(Link.class))).thenReturn(link);

        LinkResponse linkSaved = linkService.createLink("Url long");

        assertThat(linkSaved).isNotNull();
        assertThat(linkSaved.id()).isEqualTo(1L);
        assertThat(linkSaved.urlLong()).hasSizeBetween(5, 10);

        verify(linkRepository, times(1)).save(any(Link.class));
    }

    @Test
    @DisplayName("Should return Not found Exception")
    void recoverUrlOriginalNotFound() {
        when(linkRepository.findByUrlShort(anyString())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> linkService.recoverUrlOriginal("Url short", request));
    }

    @Test
    @DisplayName("Should return original URL")
    void recoverUrlOriginalSuccess() {
        String originalUrl = "Url long";
        when(linkRepository.findByUrlShort(anyString())).thenReturn(originalUrl);

        String result = linkService.recoverUrlOriginal("Url short", request);

        assertThat(result).isEqualTo(originalUrl);

        verify(linkLogService, times(1)).logClick(anyString(), any(HttpServletRequest.class));
    }
}