package com.tushar.shopcart.entity.order.item;


import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.order.OrderEntity;
import com.tushar.shopcart.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;

    @Column(length = 50)
    private String selectedColor;

    @Column(length = 50)
    private String selectedSize;

    @Column(length = 100)
    private String productNameAtPurchase; // Snapshot of product name at time of order
}