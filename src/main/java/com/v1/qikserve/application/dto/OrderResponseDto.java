package com.v1.qikserve.application.dto;

public record OrderResponseDto (
    PromotionDto promotionApplied,
    ProductsDto product,
    int quantity,
    int totalWithDiscount

){}
