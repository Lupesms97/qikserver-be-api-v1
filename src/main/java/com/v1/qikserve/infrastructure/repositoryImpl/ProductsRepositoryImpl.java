package com.v1.qikserve.infrastructure.repositoryImpl;

import com.v1.qikserve.domain.entity.ProductsEntity;
import com.v1.qikserve.domain.entity.PromotionEntity;
import com.v1.qikserve.domain.repository.ProductsRepository;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductsRepositoryImpl extends JpaRepository<ProductsEntity, UUID>, ProductsRepository {
    @Override
    @Query("SELECT p.promotions FROM ProductsEntity p WHERE p.id = :productId")
    Optional<List<PromotionEntity>> findPromotionsByProduct_Id(@Param("productId") String productId);




}
