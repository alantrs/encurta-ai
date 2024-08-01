package com.dev.encurta_ai.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_link_click_log")
public class LinkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortUrl;
    private String userAgent;
    private String referer;
    private String ipAddress;
    private LocalDateTime clickTime;

    public LinkLog() {
        this.clickTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referrer) {
        this.referer = referrer;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getClickTime() {
        return clickTime;
    }

    public void setClickTime(LocalDateTime clickTime) {
        this.clickTime = clickTime;
    }
}
