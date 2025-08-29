package com.tushar.shopcart.entity.payment;

import com.tushar.shopcart.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "card_payment_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardPaymentDetailsEntity extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethodEntity paymentMethod;

    @Column(length = 4)
    private String cardLastFour;

    @Column(length = 20)
    private String cardType; // VISA, MASTERCARD, etc.

    @Column
    private Instant expiresAt;
}