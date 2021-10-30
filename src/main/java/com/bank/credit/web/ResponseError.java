package com.bank.credit.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseError {

    private LocalDateTime timestamp;
    private String message;
    private List<String> errors;
}
