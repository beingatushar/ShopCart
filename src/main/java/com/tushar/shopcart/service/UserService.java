package com.tushar.shopcart.service;

import com.tushar.shopcart.dto.user.CreateUserDTO;
import com.tushar.shopcart.dto.user.UpdateUserDTO;
import com.tushar.shopcart.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createUser(CreateUserDTO user);

    UserEntity findById(Long userId);

    UserEntity findUserByEmail(String email);

    List<UserEntity> findAllUsers();

    UserEntity updateUser(Long userId, UpdateUserDTO user);

    void deleteUser(Long userId);
}
