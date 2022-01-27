package com.example.walletApp.config;


import org.springframework.http.HttpStatus;

public class ResponseListingDto {

    private String description;

    private HttpStatus statusCode;

    public ResponseListingDto(String description, HttpStatus status) {
        this.description = description;
        this.statusCode = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
