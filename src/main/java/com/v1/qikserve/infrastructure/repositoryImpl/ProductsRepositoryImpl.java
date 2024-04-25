package com.v1.qikserve.infrastructure.repositoryImpl;

import com.v1.qikserve.domain.entity.ProductsEntity;
import com.v1.qikserve.domain.entity.PromotionEntity;
import com.v1.qikserve.domain.repository.ProductsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepositoryImpl extends JpaRepository<ProductsEntity, String>, ProductsRepository {

        @Override
        Optional<List<PromotionEntity>> findPromotionsByProductId(String id);


}
