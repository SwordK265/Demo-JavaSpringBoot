package com.example.practicewithspring.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;

    public ExceptionResponse(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
