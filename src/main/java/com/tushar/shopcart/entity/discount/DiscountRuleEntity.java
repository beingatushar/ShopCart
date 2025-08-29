package com.tushar.shopcart.entity.discount;

import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.brand.BrandEntity;
import com.tushar.shopcart.entity.category.CategoryEntity;
import com.tushar.shopcart.entity.product.ProductEntity;
import com.tushar.shopcart.enums.DiscountRuleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "discount_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountRuleEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id", nullable = false)
    private DiscountEntity discount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DiscountRuleType ruleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity applicableProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity applicableCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity applicableBrand;
}