package com.v1.qikserve.domain.entity.promotionDetails;

import jakarta.persistence.Embeddable;



@Embeddable
public interface PromotionDetails {
    int calculateDiscount(int quantity);

}
