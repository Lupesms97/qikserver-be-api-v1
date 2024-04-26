package com.v1.qikserve.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Products {

        private String products_id;
        private String name;
        private int products_price;

        @ElementCollection
        private List<Promotion> promotionsApplied;
    }

