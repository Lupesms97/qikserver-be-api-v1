package com.v1.qikserve.presentation.mapper;

import com.v1.qikserve.application.dto.*;
import com.v1.qikserve.domain.entity.OrderEntity;
import com.v1.qikserve.domain.entity.Products;
import com.v1.qikserve.domain.entity.Promotion;

public class FromEntity {

//    public OrderResponseDto ToDto(OrderEntity orderEntity) {
//        return new OrderResponseDto(
//                ToDto(orderEntity.getPromotionApplied()),
//                ToDto(orderEntity.getProduct()),
//                orderEntity.getQuantity(),
//                orderEntity.getDiscount()
//        );
//    }

//    public PromotionDto ToDto(Promotion promotion) {
//        return new PromotionDto(
//                promotion.getPromotion_id(),
//                promotion.getType(),
//                promotion.getRequiredQty(),
//                promotion.getPromotion_price()
//        );
//    }

    public ProductsDto ToDto(Products product) {
        return new ProductsDto(
                product.getProducts_id(),
                product.getName(),
                product.getProducts_price()
        );
    }
}
