package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.address.AddressDTO;
import com.tushar.shopcart.dto.address.CreateAddressDTO;
import com.tushar.shopcart.dto.address.UpdateAddressDTO;
import com.tushar.shopcart.entity.AddressEntity;
import com.tushar.shopcart.repository.AddressRepository;
import com.tushar.shopcart.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream().map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AddressDTO findById(Long id) {
        return addressRepository.findById(id).map(address -> modelMapper.map(address, AddressDTO.class)).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public AddressDTO updateAddress(Long addressId, UpdateAddressDTO addressDTO) {
        AddressEntity addressEntity = addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(addressDTO, addressEntity);
        return modelMapper.map(addressRepository.save(addressEntity), AddressDTO.class);
    }

    @Override
    public void deleteAddress(Long addressId) {
        AddressEntity addressEntity = addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);
        addressRepository.delete(addressEntity);
    }

    @Override
    public AddressDTO createAddress(CreateAddressDTO addressDTO) {
        AddressEntity addressEntity = modelMapper.map(addressDTO, AddressEntity.class);
        return modelMapper.map(addressRepository.save(addressEntity), AddressDTO.class);
    }
}
