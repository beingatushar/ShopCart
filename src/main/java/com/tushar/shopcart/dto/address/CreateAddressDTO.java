package com.tushar.shopcart.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDTO {
    private Long userId;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String landmark;
    private Boolean isPrimary;
    private String addressType;
}
