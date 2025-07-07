package com.tushar.shopcart.repository;

import com.tushar.shopcart.entity.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, Long> {

    @Query("SELECT pr FROM ProductReviewEntity pr WHERE pr.user.id = :userId")
    List<ProductReviewEntity> findByUserId(Long userId);

    @Query("SELECT pr FROM ProductReviewEntity pr WHERE pr.product.id = :productId")
    List<ProductReviewEntity> findByProductId(Long productId);

    boolean existsById(Long productReviewId);
}