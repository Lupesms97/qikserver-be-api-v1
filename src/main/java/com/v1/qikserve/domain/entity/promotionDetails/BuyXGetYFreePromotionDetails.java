package com.v1.qikserve.domain.entity.promotionDetails;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class BuyXGetYFreePromotionDetails implements PromotionDetails {

    private int required_qty_BuyXGetYFreePromotionDetails;
    private int freeQty;

    @Override
    public int calculateDiscount(int quantity) {
        int discount = 0;
        if (quantity >= required_qty_BuyXGetYFreePromotionDetails) {
            discount = (quantity / (required_qty_BuyXGetYFreePromotionDetails + freeQty)) * freeQty;
        }
        return discount;
    }
}
