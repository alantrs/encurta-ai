package com.dev.encurta_ai.infra.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message) {
}
