package com.v1.qikserve.domain.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderEntity {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id", updatable = false, nullable = false)
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

        public OrderEntity(int quantity,
                           Products product,
                           int total,
                           boolean hasPromotion,
                           Promotion promotionApplied,
                           int totalWithDiscount,
                           int discount) {
            this.product = product;
            this.quantity = quantity;
            this.total = total;
            this.hasPromotion = hasPromotion;
            this.promotionApplied = promotionApplied;
            this.totalWithDiscount = totalWithDiscount;
            this.discount = discount;
        }



}
