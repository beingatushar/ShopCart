package com.tushar.shopcart.entity.payment;

import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.user.UserEntity;
import com.tushar.shopcart.enums.payment.PaymentMethodType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class PaymentMethodEntity extends BaseEntity {
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

    @Column
    private Instant expiresAt;

    @Column(nullable = false)
    private Boolean isActive = true;
}
