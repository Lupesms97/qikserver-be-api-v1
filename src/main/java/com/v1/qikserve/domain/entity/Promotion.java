package com.v1.qikserve.domain.entity;

import com.v1.qikserve.domain.entity.promotionDetails.PromotionDetails;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Promotion {
    @Id
    private String promotion_id;
    private String type;
    @Embedded
    private PromotionDetails details;

}
