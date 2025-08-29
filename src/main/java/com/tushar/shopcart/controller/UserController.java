package com.tushar.shopcart.controller;

import com.tushar.shopcart.entity.user.UserEntity;
import com.tushar.shopcart.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements BaseController<UserEntity, Long> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @Override
    @PostMapping
    public ResponseEntity<UserEntity> create(@Valid @RequestBody UserEntity user) {
        UserEntity createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable Long id,
                                             @Valid @RequestBody UserEntity user) {
        UserEntity updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable Long id) {
        UserEntity user = userService.get(id);
        return ResponseEntity.ok(user);
    }
}