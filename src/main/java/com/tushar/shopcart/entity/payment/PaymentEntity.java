package com.tushar.shopcart.entity.payment;

import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.order.OrderEntity;
import com.tushar.shopcart.enums.payment.PaymentMethodType;
import com.tushar.shopcart.enums.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity extends BaseEntity {
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
}