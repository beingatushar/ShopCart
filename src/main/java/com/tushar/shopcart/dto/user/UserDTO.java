package com.tushar.shopcart.dto.user;

import com.tushar.shopcart.dto.address.AddressDTO;
import com.tushar.shopcart.entity.OrderEntity;
import com.tushar.shopcart.entity.ShoppingCartEntity;
import com.tushar.shopcart.enums.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    private String username;

    private String email;

    private String phoneNumber;
    private Set<UserRole> roles;

    private List<AddressDTO> addresses;

//    private List<PaymentMethodEntity> paymentMethods = new ArrayList<>();

    private ShoppingCartEntity shoppingCart;

    private List<OrderEntity> orders;

    private Instant createdAt;

    private Instant updatedAt;
}
