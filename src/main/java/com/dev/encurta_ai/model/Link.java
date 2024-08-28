package com.dev.encurta_ai.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_link")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String urlLong;
    private String urlShort;
    @Column(columnDefinition="TEXT")
    private String urlQrCode;
    private LocalDateTime createdAt;

    public Link() {
    }

    public Link(String urlLong, String urlShort){
        this.urlLong = urlLong;
        this.urlShort = urlShort;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Link(Long id, String urlLong, String urlShort, String urlQrCode, LocalDateTime createdAt) {
        this.id = id;
        this.urlLong = urlLong;
        this.urlShort = urlShort;
        this.urlQrCode = urlQrCode;
        this.createdAt = createdAt;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlLong() {
        return urlLong;
    }

    public void setUrlLong(String urlLong) {
        this.urlLong = urlLong;
    }

    public String getUrlShort() {
        return urlShort;
    }

    public void setUrlShort(String urlShort) {
        this.urlShort = urlShort;
    }

    public String getUrlQrCode() {
        return urlQrCode;
    }

    public void setUrlQrCode(String urlQrCode) {
        this.urlQrCode = urlQrCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
