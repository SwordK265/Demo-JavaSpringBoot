package com.example.practicewithspring.model.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private Boolean access;
    private String message;
    private Object data;
}
