package com.v1.qikserve.infrastructure.repositoryImpl;

import com.v1.qikserve.domain.entity.Products;
import com.v1.qikserve.domain.repository.ProductsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepositoryImpl extends JpaRepository<Products, UUID>, ProductsRepository {
}
