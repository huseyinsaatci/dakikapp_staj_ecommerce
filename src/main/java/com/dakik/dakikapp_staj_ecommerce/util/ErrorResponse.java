package com.dakik.dakikapp_staj_ecommerce.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data()
public class ErrorResponse {
    private String message;
    private List<String> subErrors = new ArrayList<>();

    public ErrorResponse(String message) {
        this.message = message;
    }

    public void addSubError(String err) {
        subErrors.add(err);
    }
}