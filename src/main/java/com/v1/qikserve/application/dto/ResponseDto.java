package com.v1.qikserve.application.dto;


public record ResponseDto(
        String transationId,
        int status,
        boolean success,
        String promotionApplied,

        int discount,
        int total,

        int totalWithDiscount

) {
}
