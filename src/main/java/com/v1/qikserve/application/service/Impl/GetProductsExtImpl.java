package com.v1.qikserve.application.service.Impl;

import com.v1.qikserve.application.dto.ProductWithPromotionsDto;
import com.v1.qikserve.application.dto.ProductsDto;
import com.v1.qikserve.application.dto.PromotionDto;
import com.v1.qikserve.application.service.GetProductsExt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductsExtImpl {

    private final GetProductsExt getProductsExt;

    public ProductsDto getProducts() {
        return getProductsExt.getProducts();
    }

    public ProductWithPromotionsDto getProductsWithPromotion(String id) {
        ProductWithPromotionsDto promotion = getProductsExt.getProductsWithPromotion(id);

        if(promotion.promotions().isEmpty()){
            List<PromotionDto> listPromotion = List.of(new PromotionDto("0", "NO_PROMOTION", 0, 0, 0, 0));
            return new ProductWithPromotionsDto(promotion.id(), promotion.name(), promotion.price(), listPromotion);
        }

        return promotion;

    }


}
