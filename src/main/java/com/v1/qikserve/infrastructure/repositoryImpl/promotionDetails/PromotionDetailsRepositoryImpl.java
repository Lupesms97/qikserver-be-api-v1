package com.v1.qikserve.infrastructure.repositoryImpl.promotionDetails;

import com.v1.qikserve.domain.entity.promotionDetails.PromotionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface PromotionDetailsRepositoryImpl extends JpaRepository<PromotionDetails, UUID> {
}
