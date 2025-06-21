package com.tushar.shopcart.service;

import com.tushar.shopcart.dto.user.CreateUserDTO;
import com.tushar.shopcart.dto.user.UpdateUserDTO;
import com.tushar.shopcart.dto.user.UserDTO;
import com.tushar.shopcart.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserDTO createUser(CreateUserDTO user);

    UserDTO findById(Long userId);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAllUsers();

    UserDTO updateUser(Long userId, UpdateUserDTO user);

    void deleteUser(Long userId);
}
