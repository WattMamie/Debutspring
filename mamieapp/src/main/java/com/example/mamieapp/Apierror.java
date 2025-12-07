package com.example.mamieapp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Apierror {
    private String message;
    private int code;
    private LocalDateTime timestamp;
}
