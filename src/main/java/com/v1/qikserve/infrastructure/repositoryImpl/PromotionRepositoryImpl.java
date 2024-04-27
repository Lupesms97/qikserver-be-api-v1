package com.v1.qikserve.infrastructure.repositoryImpl;

import com.v1.qikserve.domain.entity.Promotion;
import com.v1.qikserve.domain.repository.PromotionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepositoryImpl extends JpaRepository<Promotion, String> , PromotionRepository {
}
