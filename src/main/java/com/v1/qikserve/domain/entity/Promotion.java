package com.v1.qikserve.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Embeddable
public class Promotion {

    private String promotion_id;
    private String type;
    private int requiredQty;
    private int promotion_price;

}
