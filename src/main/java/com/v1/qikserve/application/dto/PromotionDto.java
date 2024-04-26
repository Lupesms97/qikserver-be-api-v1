package com.v1.qikserve.application.dto;

public record PromotionDto(
       String id,
       String type,
       int required_qty,
       int price) {

}
