package com.v1.qikserve.domain.entity.promotionDetails;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue("FlatPercentPromotionDetails")
public class FlatPercentPromotionDetails extends PromotionDetails {

    int amount;
    @Override
    public int calculateDiscount(int quantity, int price) {
 //       DESCONTO SEM A PROCENTAGEM
        return amount;
    }
}
