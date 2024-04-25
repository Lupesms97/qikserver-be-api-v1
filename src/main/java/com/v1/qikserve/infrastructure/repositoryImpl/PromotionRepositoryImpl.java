package com.v1.qikserve.infrastructure.repositoryImpl;

import com.v1.qikserve.domain.entity.PromotionEntity;
import com.v1.qikserve.domain.repository.PromotionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepositoryImpl extends JpaRepository<PromotionEntity, String>, PromotionRepository{

    @Override
    Optional<List<PromotionEntity>> findPromotionsByProductId(String id);

}
