package com.v1.qikserve.domain.entity.promotionDetails;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Embeddable
public class NoPromotionDetails implements PromotionDetails {
    @Override
    public int calculateDiscount(int quantity) {
        return 0;
    }
}
