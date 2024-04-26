package com.v1.qikserve.domain.repository;

import com.v1.qikserve.domain.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrderRepository  {
    Optional<OrderEntity> findOrderByProductId(UUID id);

}
