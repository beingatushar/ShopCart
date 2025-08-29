package com.tushar.shopcart.entity.payment;

import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.user.UserEntity;
import com.tushar.shopcart.enums.payment.PaymentMethodType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethodEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethodType type;

    @Column(nullable = false, length = 100)
    private String displayName;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDefault = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @OneToOne(mappedBy = "paymentMethod", cascade = CascadeType.ALL, orphanRemoval = true)
    private CardPaymentDetailsEntity cardDetails;
}