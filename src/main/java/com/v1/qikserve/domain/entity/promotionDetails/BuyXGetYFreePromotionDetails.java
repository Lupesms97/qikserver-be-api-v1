package com.v1.qikserve.domain.entity.promotionDetails;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DiscriminatorValue("BuyXGetYFreePromotionDetails")
public class BuyXGetYFreePromotionDetails extends PromotionDetails {

    private int required_qty;
    private int freeQty;

    @Override
    public int calculateDiscount(int quantity, int price ) {
    // RETORNA A QUANTIDADE GRATIUITA
        if (quantity >= required_qty) {
            return (quantity / required_qty);
        } else {
            return 0;
        }
    }
}
