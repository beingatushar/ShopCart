package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.user.CreateUserDTO;
import com.tushar.shopcart.dto.user.UpdateUserDTO;
import com.tushar.shopcart.dto.user.UserDTO;
import com.tushar.shopcart.entity.UserEntity;
import com.tushar.shopcart.enums.user.UserStatus;
import com.tushar.shopcart.exception.DuplicateResourceException;
import com.tushar.shopcart.repository.UserRepository;
import com.tushar.shopcart.service.UserService;
import com.tushar.shopcart.utils.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper,
                           UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO createUser(CreateUserDTO userDTO) {
        // Check if username exists
        userRepository.findByUsername(userDTO.getUsername())
                .ifPresent(u -> {
                    throw new DuplicateResourceException("Username '" + userDTO.getUsername() + "' already exists");
                });

        // Check if email exists
        userRepository.findByEmail(userDTO.getEmail())
                .ifPresent(u -> {
                    throw new DuplicateResourceException("Email '" + userDTO.getEmail() + "' already exists");
                });

        UserEntity userEntity = modelMapper.mapToUserEntity(userDTO);
        UserEntity savedUser = userRepository.save(userEntity);
        return modelMapper.mapToUserDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long userId) {
        return modelMapper.mapToUserDTO(
                userRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUserByEmail(String email) {
        return modelMapper.mapToUserDTO(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(modelMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long userId, UpdateUserDTO userDTO) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Check if new username is taken by another user
        if (userDTO.getUsername() != null && !user.getUsername().equals(userDTO.getUsername())) {
            userRepository.findByUsername(userDTO.getUsername())
                    .ifPresent(u -> {
                        throw new DuplicateResourceException("Username '" + userDTO.getUsername() + "' already exists");
                    });
            user.setUsername(userDTO.getUsername());
        }

        // Check if new email is taken by another user
        if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
            userRepository.findByEmail(userDTO.getEmail())
                    .ifPresent(u -> {
                        throw new DuplicateResourceException("Email '" + userDTO.getEmail() + "' already exists");
                    });
            user.setEmail(userDTO.getEmail());
        }

        // Update other fields
        modelMapper.updateUserEntity(userDTO, user);

        UserEntity updatedUser = userRepository.save(user);
        return modelMapper.mapToUserDTO(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);
    }
}