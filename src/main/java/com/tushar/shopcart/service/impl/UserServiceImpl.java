package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.entity.user.UserEntity;
import com.tushar.shopcart.repository.UserRepository;
import com.tushar.shopcart.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAll() {
        log.debug("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity create(UserEntity entity) {
        log.debug("Creating new user: {}", entity.getUsername());
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public UserEntity update(Long id, UserEntity user) {
        log.debug("Updating user with ID: {}", id);

        // Check if user exists first
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID: " + id + " not found");
        }

        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID: " + id + " not found");
        }

        userRepository.deleteById(id);
        log.info("Successfully deleted user with ID: {}", id);
    }

    @Override
    public UserEntity get(Long id) {
        log.debug("Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + id + " not found"));
    }
}