package com.v1.qikserve.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        private int quantity;

        @OneToOne(cascade = CascadeType.ALL)
        private Products product;

        private int total;
        private boolean hasPromotion;


        @OneToOne(cascade = CascadeType.ALL)
        private Promotion promotionApplied;
        private int totalWithDiscount;
        private int discount;




}
