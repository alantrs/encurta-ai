package com.dev.encurta_ai.controller;

import com.dev.encurta_ai.dto.LinkResponse;
import com.dev.encurta_ai.model.Link;
import com.dev.encurta_ai.service.LinkService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/encurta-ai")
    public ResponseEntity<LinkResponse> createLink(@RequestBody Map<String, String > request){
        String urlOriginal = request.get("urlOriginal");
        LinkResponse linkResponse = linkService.createLink(urlOriginal);
        return ResponseEntity.status(HttpStatus.CREATED).body(linkResponse);
    }

    @GetMapping("/r/{urlShort}")
    public void redirect(@PathVariable String urlShort, HttpServletRequest request, HttpServletResponse response){
        String urlOriginal = linkService.recoverUrlOriginal(urlShort, request);
        try {
            response.sendRedirect(urlOriginal);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
