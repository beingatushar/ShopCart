package com.tushar.shopcart.repository;

import com.tushar.shopcart.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @EntityGraph(attributePaths = {"category", "brand", "images", "attributes", "reviews"})
    Optional<ProductEntity> findById(Long id);

    @EntityGraph(attributePaths = {"category", "brand"})
    List<ProductEntity> findAll();
}