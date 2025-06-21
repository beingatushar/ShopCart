package com.tushar.shopcart.controller;

import com.tushar.shopcart.dto.user.CreateUserDTO;
import com.tushar.shopcart.dto.user.UpdateUserDTO;
import com.tushar.shopcart.dto.user.UserDTO;
import com.tushar.shopcart.entity.UserEntity;
import com.tushar.shopcart.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Endpoints for managing users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDTO user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid UpdateUserDTO user) {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
    }

}
