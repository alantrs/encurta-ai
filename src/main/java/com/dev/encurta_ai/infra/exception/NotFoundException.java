package com.dev.encurta_ai.infra.exception;

public class NotFoundException extends RuntimeException{

    private String msg;

    public NotFoundException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
