package com.tushar.shopcart.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 200)
    private String logoUrl;

    @Column(nullable = false)
    private Boolean isActive = true;
    @JsonManagedReference(value = "brand-products")
    @OneToMany(mappedBy = "brand")
    private List<ProductEntity> products;
}
