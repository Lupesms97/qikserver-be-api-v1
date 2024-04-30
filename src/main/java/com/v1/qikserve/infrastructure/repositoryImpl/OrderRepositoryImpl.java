package com.v1.qikserve.infrastructure.repositoryImpl;

import com.v1.qikserve.domain.entity.OrderEntity;
import com.v1.qikserve.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepositoryImpl extends JpaRepository<OrderEntity, UUID>, OrderRepository {
    @Override
    @Query("SELECT o FROM OrderEntity o WHERE o.product.products_id = :id")
    Optional<OrderEntity> findOrderByProductId(UUID id);
}
