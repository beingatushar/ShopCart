package com.tushar.shopcart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "product_attributes")
@Setter 
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttributeEntity extends BaseEntity{
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 200)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private ProductEntity product;
}

