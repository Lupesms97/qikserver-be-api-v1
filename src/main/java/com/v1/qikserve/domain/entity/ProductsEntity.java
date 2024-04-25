package com.v1.qikserve.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductsEntity {
    @Id
    @Column(name = "products_id")
    private String identifier_id;
    private String api_id;
    private String name;
    private int price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private List<PromotionEntity> promotions;
}
