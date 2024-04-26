package com.v1.qikserve.application.service;


import com.v1.qikserve.application.dto.ProductWithPromotionsDto;
import com.v1.qikserve.application.dto.ProductsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "products", url = "${api.mock.url}")
public interface GetProductsExt {

    @RequestMapping(value = "/products", method = GET, consumes = "application/json", produces = "application/json")
    ProductsDto getProducts();

    @RequestMapping(value = "/products/{id}", method = GET, consumes = "application/json", produces = "application/json")
    ProductWithPromotionsDto getProductsWithPromotion(@PathVariable String id);

}
