package com.v1.qikserve.domain.entity.promotionDetails;

import com.v1.qikserve.application.enums.TypePromotion;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "promotionDetails_type",
        discriminatorType = DiscriminatorType.STRING
)
@NoArgsConstructor
@Getter
@Setter
public abstract class PromotionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255)")
    private TypePromotion type;
    private int price;

    public int calculateDiscount(int quantity, int price ) {
        return 0;
    }

}
