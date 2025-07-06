package com.tushar.shopcart.entity;

import com.tushar.shopcart.enums.inventory.InventoryChangeType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private Integer reservedQuantity = 0;

    @Column(nullable = false)
    private Integer soldQuantity = 0;

    @Column(nullable = false)
    private Integer minimumStockLevel = 10;

    @Column(nullable = false)
    private Instant lastStockUpdate;

    @Version
    private Long version;
}

@Entity
@Table(name = "inventory_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
class InventoryHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String referenceId; // Could be order ID, adjustment ID, etc.

    @Column(nullable = false)
    private Instant changeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by_user_id")
    private UserEntity changedBy;

    @Version
    private Long version;
}