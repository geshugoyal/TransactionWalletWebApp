package com.example.walletApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class PSQLException extends RuntimeException{

    public PSQLException(String message) {
        super(message);
    }
}
