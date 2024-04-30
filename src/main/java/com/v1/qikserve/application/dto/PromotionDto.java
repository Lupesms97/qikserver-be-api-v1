package com.v1.qikserve.application.dto;

import jakarta.annotation.Nullable;


public record PromotionDto(
       String id,

       String type,
       @Nullable
       int required_qty,
       @Nullable
       int price,
       @Nullable
       int amount,
       @Nullable
       int free_qty
       ) {

}
