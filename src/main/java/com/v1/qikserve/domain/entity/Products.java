package com.v1.qikserve.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Products {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        private String products_id;
        private String name;
        private int products_price;

        @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
        @JoinColumn(name = "products_id")
        private List<Promotion> promotionsApplicable;


        public Products(String products_id, String name, int products_price, List<Promotion> promotionsApplicable) {
            this.products_id = products_id;
            this.name = name;
            this.products_price = products_price;
            this.promotionsApplicable = promotionsApplicable;
        }

    }

