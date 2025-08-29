package com.tushar.shopcart.entity.product.attribute;

import com.tushar.shopcart.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeEntity extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name; // e.g., "Color"

    @Column(nullable = false, length = 200)
    private String value; // e.g., "Red"
}