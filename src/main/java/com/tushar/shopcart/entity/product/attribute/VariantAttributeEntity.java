package com.tushar.shopcart.entity.product.attribute;

import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.product.variant.ProductVariantEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variant_attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantAttributeEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariantEntity variant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    private AttributeEntity attribute;
}