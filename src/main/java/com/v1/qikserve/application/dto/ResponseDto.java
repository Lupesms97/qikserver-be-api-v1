package com.v1.qikserve.application.dto;

public record ResponseDto(
        String transationId,
        String message,
        int status,
        boolean success,
        int discount,
        int total

) {
}
