package com.tushar.shopcart.entity.inventory;

import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.product.ProductEntity;
import com.tushar.shopcart.enums.inventory.InventoryChangeType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "inventory_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryHistoryEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer quantityChange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InventoryChangeType changeType;

    @Column(length = 500)
    private String reason;

    @Column(nullable = false)
    private String referenceId;
}