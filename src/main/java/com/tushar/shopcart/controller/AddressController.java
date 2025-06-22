package com.tushar.shopcart.controller;

import com.tushar.shopcart.dto.address.AddressDTO;
import com.tushar.shopcart.dto.address.CreateAddressDTO;
import com.tushar.shopcart.dto.address.UpdateAddressDTO;
import com.tushar.shopcart.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@Tag(name = "Address Management", description = "Endpoints for managing addresses")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    ResponseEntity<List<AddressDTO>> findAll() {
        return new ResponseEntity<>(addressService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    ResponseEntity<AddressDTO> findById(@PathVariable Long addressId) {
        return new ResponseEntity<>(addressService.findById(addressId), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<AddressDTO> createAddress(@RequestBody CreateAddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.createAddress(addressDTO), HttpStatus.OK);
    }

    @PutMapping("/{addressId}")
    ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId, @RequestBody UpdateAddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.updateAddress(addressId, addressDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
