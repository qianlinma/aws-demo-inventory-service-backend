package com.mycompany.inventory.demo.controller;

public record ApiErrorResponse(
        String error,
        String message
) {
}
