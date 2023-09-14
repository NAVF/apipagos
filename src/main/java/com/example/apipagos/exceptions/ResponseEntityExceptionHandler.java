package com.example.apipagos.exceptions;

import com.example.apipagos.dto.ExceptionDto;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(value = GenericExceptions.class)
    public ResponseEntity<ExceptionDto> genericExceptionHandler(GenericExceptions ex){
        ExceptionDto exception = ExceptionDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(exception, ex.getStatus());
    }

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<ExceptionDto> feignExceptionHandler(FeignException ex){
        ExceptionDto exception = ExceptionDto.builder().code("E500_C").message(ex.getMessage()).build();
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
