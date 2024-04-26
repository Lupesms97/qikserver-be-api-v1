package com.v1.qikserve.application.service.Impl;

import com.v1.qikserve.application.dto.ProductWithPromotionsDto;
import com.v1.qikserve.application.dto.ProductsDto;
import com.v1.qikserve.application.service.GetProductsExt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductsExtImpl {

    private final GetProductsExt getProductsExt;

    public ProductsDto getProducts() {
        return getProductsExt.getProducts();
    }

    public ProductWithPromotionsDto getProductsWithPromotion(String id) {
        ProductWithPromotionsDto product = getProductsExt.getProductsWithPromotion(id);
        System.out.println(product);
        return product;
    }


}
