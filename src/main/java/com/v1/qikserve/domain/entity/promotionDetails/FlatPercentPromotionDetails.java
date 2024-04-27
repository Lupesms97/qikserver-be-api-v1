package com.v1.qikserve.domain.entity.promotionDetails;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class FlatPercentPromotionDetails implements PromotionDetails {

    int amount;
    @Override
    public int calculateDiscount(int quantity) {
        return (quantity * amount) / 100;
    }
}
