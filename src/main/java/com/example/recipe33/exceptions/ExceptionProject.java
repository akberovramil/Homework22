package com.example.recipe33.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionProject extends Exception {



    public ExceptionProject(String message) {
        super(message);
    }
}
