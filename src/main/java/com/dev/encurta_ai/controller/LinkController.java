package com.dev.encurta_ai.controller;

import com.dev.encurta_ai.dto.LinkResponse;
import com.dev.encurta_ai.model.Link;
import com.dev.encurta_ai.service.LinkService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("*")
@Tag(name = "Link Controller")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/encurta-ai")
    @Operation(summary = "Shorten a URL")
    public ResponseEntity<LinkResponse> createLink(@RequestParam String urlOriginal, HttpServletRequest request){
        LinkResponse linkResponse = linkService.createLink(urlOriginal, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(linkResponse);
    }

    @GetMapping("{urlShort}")
    @Hidden
    public ResponseEntity<Void> redirect(@PathVariable String urlShort, HttpServletRequest request) {
        String urlOriginal = linkService.recoverUrlOriginal(urlShort, request);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlOriginal));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}
