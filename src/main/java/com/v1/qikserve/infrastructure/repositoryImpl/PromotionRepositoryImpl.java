package com.v1.qikserve.infrastructure.repositoryImpl;

import com.v1.qikserve.domain.entity.Promotion;
import com.v1.qikserve.domain.repository.PromotionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromotionRepositoryImpl extends JpaRepository<Promotion, UUID> , PromotionRepository {
}
