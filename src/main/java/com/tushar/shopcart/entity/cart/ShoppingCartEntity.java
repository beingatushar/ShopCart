package com.tushar.shopcart.entity.cart;

import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.product.ProductEntity;
import com.tushar.shopcart.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartEntity extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt DESC")
    private List<CartItemEntity> items = new ArrayList<>();

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal shippingAmount = BigDecimal.ZERO;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer itemCount = 0;

    public void calculateTotals() {
        this.subtotal = items.stream()
                .map(CartItemEntity::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.total = subtotal
                .add(taxAmount != null ? taxAmount : BigDecimal.ZERO)
                .add(shippingAmount != null ? shippingAmount : BigDecimal.ZERO);

        this.itemCount = items.stream()
                .mapToInt(CartItemEntity::getQuantity)
                .sum();
    }
}

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class CartItemEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private ShoppingCartEntity cart;

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

    @PrePersist
    @PreUpdate
    private void calculateTotalPrice() {
        this.totalPrice = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }
}