package com.tushar.shopcart.entity.product.image;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageEntity extends BaseEntity {
    @Column(nullable = false)
    private String imageUrl;

    @Column(length = 100)
    private String altText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference // prevents infinite recursion during serialization
    private ProductEntity product;
}

