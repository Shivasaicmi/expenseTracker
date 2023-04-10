package com.expenseTracker.backend.models;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private HttpStatus statusCode;

    private String errorMessage;

    private LocalDateTime date = LocalDateTime.now();

    public ErrorResponse(HttpStatus status,String errorMessage){
        this.errorMessage = errorMessage;
        this.statusCode = status;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", date=" + date +
                '}';
    }
}
