package com.v1.qikserve.application.dto;


import java.util.List;

public record ProductWithPromotionsDto(
        String id,
        String name,
        int price,
        List<PromotionDto> promotions

) {

}
