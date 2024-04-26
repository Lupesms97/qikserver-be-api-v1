package com.v1.qikserve.presentation.mapper;

import com.v1.qikserve.application.dto.OrderDto;
import com.v1.qikserve.application.dto.ProductWithPromotionsDto;
import com.v1.qikserve.application.dto.PromotionDto;
import com.v1.qikserve.domain.IdentifierProducer;
import com.v1.qikserve.domain.entity.OrderEntity;
import com.v1.qikserve.domain.entity.Products;
import com.v1.qikserve.domain.entity.Promotion;

public class FromDto {


    public OrderEntity ToEntity(OrderDto orderDto, ProductWithPromotionsDto product, int total, boolean hasPromotion, int totalWithDiscount, int discount, PromotionDto promotionApplied) {
        IdentifierProducer identifierProducer = IdentifierProducer.createInstance();
        Products products = new FromDto().ToEntity(product);

        if (!hasPromotion){
            promotionApplied = new PromotionDto("0", "none", 0, 0);
        }

        Promotion promotion = new FromDto().ToEntity(promotionApplied);
        return new OrderEntity(
                identifierProducer.getIdentifier(),
                orderDto.quantity(),
                products,
                total,
                hasPromotion,
                promotion,
                totalWithDiscount,
                discount
        );
    }


    public Products ToEntity(ProductWithPromotionsDto productsDto) {
        return new Products(
                productsDto.id(),
                productsDto.name(),
                productsDto.price(),
                productsDto.promotions().stream().map(promotionDto -> Promotion.builder()
                        .promotion_id(promotionDto.id())
                        .type(promotionDto.type())
                        .requiredQty(promotionDto.required_qty())
                        .promotion_price(promotionDto.price())
                        .build()).toList()

        );
    }


    public Promotion ToEntity(PromotionDto promotionDto) {
        return new Promotion(
                promotionDto.id(),
                promotionDto.type(),
                promotionDto.required_qty(),
                promotionDto.price()
        );

    }
}
