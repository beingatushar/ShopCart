package com.tushar.shopcart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethodType method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column(nullable = false, unique = true, length = 50)
    private String transactionId;

    @Column(length = 500)
    private String transactionDetails;

    @Column(nullable = false)
    private Instant paymentDate;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    public enum PaymentMethodType {
        CREDIT_CARD, DEBIT_CARD, PAYPAL,
        BANK_TRANSFER, APPLE_PAY, GOOGLE_PAY,
        CRYPTO, GIFT_CARD
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED,
        REFUNDED, PARTIALLY_REFUNDED
    }
}

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethodType type;

    @Column(nullable = false, length = 100)
    private String displayName;

    @Column(length = 100)
    private String cardLastFour; // For card payments

    @Column(length = 20)
    private String cardType; // VISA, MASTERCARD, etc.

    @Column(nullable = false)
    private Boolean isDefault = false;

    @Column(nullable = false)
    private Instant createdAt;

    @Column
    private Instant expiresAt;

    @Column(nullable = false)
    private Boolean isActive = true;

    public enum PaymentMethodType {
        CREDIT_CARD, DEBIT_CARD, PAYPAL,
        BANK_ACCOUNT, APPLE_PAY, GOOGLE_PAY
    }
}