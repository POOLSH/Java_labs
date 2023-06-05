package com.example.first_lab.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class OtherException extends RuntimeException {
    private final Logger logger = LogManager.getLogger(OtherException.class);
    public OtherException(String message) {
        super(message);
        logger.warn(message);
    }
}
