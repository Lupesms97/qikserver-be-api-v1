package com.v1.qikserve.presentation.mapper;

import com.v1.qikserve.application.dto.OrderDto;
import com.v1.qikserve.application.dto.ProductWithPromotionsDto;
import com.v1.qikserve.application.dto.PromotionDto;
import com.v1.qikserve.domain.IdentifierProducer;
import com.v1.qikserve.domain.entity.OrderEntity;
import com.v1.qikserve.domain.entity.Products;
import com.v1.qikserve.domain.entity.Promotion;
import com.v1.qikserve.domain.entity.promotionDetails.*;

import static java.util.stream.Collectors.toList;

public class FromDto {


    public OrderEntity ToEntity(OrderDto orderDto,
                                ProductWithPromotionsDto productWithPromotionsDto,
                                int total,
                                boolean hasPromotion,
                                int totalWithDiscount,
                                int discount,
                                Promotion promotionApplied) {
        IdentifierProducer identifierProducer = IdentifierProducer.createInstance();
        Products products = new FromDto().ToEntity(productWithPromotionsDto);

        if (!hasPromotion){
            promotionApplied = new Promotion();
        }


        return new OrderEntity(
                identifierProducer.getIdentifier(),
                orderDto.quantity(),
                products,
                total,
                hasPromotion,
                promotionApplied,
                totalWithDiscount,
                discount
        );
    }


    public Products ToEntity(ProductWithPromotionsDto productsDto) {
        Products product =  new Products(
                productsDto.id(),
                productsDto.name(),
                productsDto.price(),
                productsDto.promotions().stream().map(promotionDto -> Promotion.builder()
                        .promotion_id(promotionDto.id())
                        .type(promotionDto.type())
                        .details(GetPromotionsDetails(promotionDto.type(), promotionDto))
                        .build()).toList());

        System.out.println(product);
        return product;
    }




    private PromotionDetails GetPromotionsDetails(String type, PromotionDto promotionDto) {
        switch (type) {
            case "QTY_BASED_PRICE_OVERRIDE":
                return new QtyBasedPriceOverridePromotionDetails(promotionDto.required_qty(), promotionDto.price());
            case "FLAT_PERCENT":
                return new FlatPercentPromotionDetails(promotionDto.amount());
            case "BUY_X_GET_Y_FREE":
                return new BuyXGetYFreePromotionDetails(promotionDto.required_qty(), promotionDto.free_qty());
            default:
                return new NoPromotionDetails();
        }


    }
}
