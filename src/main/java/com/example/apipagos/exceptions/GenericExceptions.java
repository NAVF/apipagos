package com.example.apipagos.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericExceptions extends RuntimeException{
    private String code;
    private HttpStatus status;

    public GenericExceptions(String code, HttpStatus status, String message){
        super(message);
        this.code = code;
        this.status = status;
    }
}
