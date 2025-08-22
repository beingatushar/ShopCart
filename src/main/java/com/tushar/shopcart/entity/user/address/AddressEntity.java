package com.tushar.shopcart.entity.user.address;

import com.tushar.shopcart.entity.BaseEntity;
import com.tushar.shopcart.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AddressEntity extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String state;

    @Column(nullable = false, length = 20)
    private String postalCode;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(length = 100)
    private String landmark;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isPrimary = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @Column(length = 50)
    private String addressType;
}