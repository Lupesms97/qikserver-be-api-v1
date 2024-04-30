package com.v1.qikserve.domain.entity.promotionDetails;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "QtyBasedPriceOverride")
@DiscriminatorValue("QtyBasedPriceOverride")
public class QtyBasedPriceOverridePromotionDetails extends PromotionDetails {

    private int required_qty;
    private int price;

    @Override
    public int calculateDiscount(int quantity, int price ){
        if (quantity >= required_qty) {
            return quantity / required_qty;

        } else {
            return 0;
        }
    }
}
