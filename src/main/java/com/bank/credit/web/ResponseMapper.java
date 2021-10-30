package com.bank.credit.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMapper {
    public static ResponseEntity<Object> errorToEntity(ResponseError error, HttpStatus status) {
        return new ResponseEntity<>(error, status);
    }

}
