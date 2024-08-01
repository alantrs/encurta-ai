package com.dev.encurta_ai.service;

import com.dev.encurta_ai.model.LinkLog;
import com.dev.encurta_ai.repository.LinkLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkLogService {

    @Autowired
    private LinkLogRepository linkLogRepository;

    public void logClick(String urlShort, HttpServletRequest request) {
        LinkLog linkLog = new LinkLog();
        linkLog.setShortUrl(urlShort);
        linkLog.setUserAgent(request.getHeader("User-Agent"));
        linkLog.setReferer(request.getHeader("Referer"));
        linkLog.setIpAddress(request.getRemoteAddr());
        linkLogRepository.save(linkLog);
    }
}
