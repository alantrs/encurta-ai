package com.dev.encurta_ai.service;

import com.dev.encurta_ai.model.LinkLog;
import com.dev.encurta_ai.repository.LinkLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class LinkLogServiceTest {

    @InjectMocks
    private LinkLogService linkLogService;

    @Mock
    private LinkLogRepository linkLogRepository;

    @Mock
    private HttpServletRequest request;

    @Test
    @DisplayName("Should create log")
    void createLogClickSuccess() {

        String urlShort = "http://short.url";
        when(request.getHeader("User-Agent")).thenReturn("Test User Agent");
        when(request.getHeader("Referer")).thenReturn("http://referer.url");
        when(request.getRemoteAddr()).thenReturn("192.168.1.1");

        linkLogService.logClick(urlShort, request);

        ArgumentCaptor<LinkLog> linkLogCaptor = ArgumentCaptor.forClass(LinkLog.class);
        verify(linkLogRepository, times(1)).save(linkLogCaptor.capture());
        LinkLog capturedLinkLog = linkLogCaptor.getValue();

        assertEquals(urlShort, capturedLinkLog.getShortUrl(), "The short URL should match");
        assertEquals("Test User Agent", capturedLinkLog.getUserAgent(), "The User-Agent should match");
        assertEquals("http://referer.url", capturedLinkLog.getReferer(), "The Referer should match");
        assertEquals("192.168.1.1", capturedLinkLog.getIpAddress(), "The IP address should match");
    }
}