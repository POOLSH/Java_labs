package com.example.first_lab.Exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegativeException extends RuntimeException {
    private final Logger logger = LogManager.getLogger(NegativeException.class);
    public NegativeException(String message) {
        super(message);
        logger.warn(message);
    }

}

