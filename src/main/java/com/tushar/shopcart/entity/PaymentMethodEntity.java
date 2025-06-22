package com.tushar.shopcart.entity;

import com.tushar.shopcart.enums.payment.PaymentMethodType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
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
}
