package com.tushar.shopcart.entity.product.attribute;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_attributes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttributeEntity extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 200)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private ProductEntity product;
}

