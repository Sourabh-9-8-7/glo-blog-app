package com.blog.app.bloggingapplication.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {
    Date timestamp;
    String message;
    String description;

    public ErrorDetails(Date timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}
