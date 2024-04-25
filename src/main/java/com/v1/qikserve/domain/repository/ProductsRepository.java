package com.v1.qikserve.domain.repository;

import com.v1.qikserve.domain.entity.PromotionEntity;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {

    Optional<List<PromotionEntity>> findPromotionsByProductId(String id);

}
