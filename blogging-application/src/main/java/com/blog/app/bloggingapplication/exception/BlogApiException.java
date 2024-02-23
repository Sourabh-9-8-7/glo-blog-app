package com.blog.app.bloggingapplication.exception;

import lombok.Getter;

@Getter
public class BlogApiException extends RuntimeException{
    private String message;

    public BlogApiException(String message) {
        super(message);
        this.message = message;
    }
}
