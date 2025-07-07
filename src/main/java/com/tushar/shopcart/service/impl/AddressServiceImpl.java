package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.address.AddressDTO;
import com.tushar.shopcart.dto.address.CreateAddressDTO;
import com.tushar.shopcart.dto.address.UpdateAddressDTO;
import com.tushar.shopcart.entity.AddressEntity;
import com.tushar.shopcart.entity.UserEntity;
import com.tushar.shopcart.repository.AddressRepository;
import com.tushar.shopcart.repository.UserRepository;
import com.tushar.shopcart.service.AddressService;
import com.tushar.shopcart.utils.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressServiceImpl(UserRepository userRepository,
                              AddressRepository addressRepository,
                              ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream()
                .map(modelMapper::mapToAddressDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO findById(Long id) {
        AddressEntity address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
        return modelMapper.mapToAddressDTO(address);
    }

    @Override
    public AddressDTO updateAddress(Long addressId, UpdateAddressDTO addressDTO) {
        AddressEntity addressEntity = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressId));

        modelMapper.updateAddressEntity(addressDTO, addressEntity);
        AddressEntity updatedAddress = addressRepository.save(addressEntity);
        return modelMapper.mapToAddressDTO(updatedAddress);
    }

    @Override
    public void deleteAddress(Long addressId) {
        AddressEntity addressEntity = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressId));
        addressRepository.delete(addressEntity);
    }

    @Transactional
    @Override
    public AddressDTO createAddress(CreateAddressDTO addressDTO) {
        UserEntity user = userRepository.findById(addressDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + addressDTO.getUserId()));

        AddressEntity address = modelMapper.mapToAddressEntity(addressDTO);
        address.setUser(user);

        AddressEntity savedAddress = addressRepository.save(address);
        return modelMapper.mapToAddressDTO(savedAddress);
    }
}