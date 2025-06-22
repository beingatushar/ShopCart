package com.tushar.shopcart.service;

import com.tushar.shopcart.dto.address.AddressDTO;
import com.tushar.shopcart.dto.address.CreateAddressDTO;
import com.tushar.shopcart.dto.address.UpdateAddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> findAll();

    AddressDTO findById(Long id);

    AddressDTO updateAddress(Long addressId, UpdateAddressDTO addressDTO);

    void deleteAddress(Long addressId);

    AddressDTO createAddress(CreateAddressDTO addressDTO);
}
