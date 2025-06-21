package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.user.CreateUserDTO;
import com.tushar.shopcart.dto.user.UpdateUserDTO;
import com.tushar.shopcart.entity.UserEntity;
import com.tushar.shopcart.repository.UserRepository;
import com.tushar.shopcart.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(CreateUserDTO user) {
        UserEntity createdUser = new UserEntity();
        modelMapper.map(user, createdUser);
        return userRepository.save(createdUser);
    }

    @Override
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(Long userId, UpdateUserDTO user) {
        UserEntity userToUpdate = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(user, userToUpdate);
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity userToDelete = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(userToDelete);
    }
}
