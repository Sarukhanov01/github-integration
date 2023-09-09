package com.atipera.githubintegration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UnsupportedMediaTypeException extends RuntimeException {
    public UnsupportedMediaTypeException() {
        super("Unsupported media type: application/xml");
    }
}
