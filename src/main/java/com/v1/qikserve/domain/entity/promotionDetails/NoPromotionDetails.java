package com.v1.qikserve.domain.entity.promotionDetails;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;



@Entity
@DiscriminatorValue("NoPromotionDetails")
public class NoPromotionDetails extends PromotionDetails {

}
