package com.v1.qikserve.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Products {
        @Id
        private String products_id;
        private String name;
        private int products_price;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "products_id")
        private List<Promotion> promotionsApplicable;

    }

