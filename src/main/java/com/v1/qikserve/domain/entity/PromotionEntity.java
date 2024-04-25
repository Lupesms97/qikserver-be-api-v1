package com.v1.qikserve.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PromotionEntity {
    @Id
    private String identifier_id;
    private String api_id;
    private String type;
    private int requiredQty;
    private int price;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private ProductsEntity productEntity;
}
