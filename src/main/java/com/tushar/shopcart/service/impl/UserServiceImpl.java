package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.user.CreateUserDTO;
import com.tushar.shopcart.dto.user.UpdateUserDTO;
import com.tushar.shopcart.dto.user.UserDTO;
import com.tushar.shopcart.entity.UserEntity;
import com.tushar.shopcart.enums.user.UserStatus;
import com.tushar.shopcart.repository.UserRepository;
import com.tushar.shopcart.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(CreateUserDTO user) {
        UserEntity createdUser = new UserEntity();
        modelMapper.map(user, createdUser);
        return modelMapper.map(userRepository.save(createdUser), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long userId) {
        return modelMapper.map(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new), UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return modelMapper.map(userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(u -> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long userId, UpdateUserDTO user) {
        UserEntity userToUpdate = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(user, userToUpdate);
        return modelMapper.map(userRepository.save(userToUpdate), UserDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity userToDelete = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userToDelete.setStatus(UserStatus.DELETED);
    }
}
