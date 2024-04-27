package com.v1.qikserve.domain.entity.promotionDetails;


import com.v1.qikserve.domain.entity.promotionDetails.PromotionDetails;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class QtyBasedPriceOverridePromotionDetails implements PromotionDetails {

    private int required_qty_QtyBasedPriceOverridePromotionDetails;
    private int price;
    @Override
    public int calculateDiscount(int quantity){
        int discount = 0;
        if (quantity >= required_qty_QtyBasedPriceOverridePromotionDetails) {
            discount = (quantity - required_qty_QtyBasedPriceOverridePromotionDetails) * price;
        }
        return discount;
    }
}
