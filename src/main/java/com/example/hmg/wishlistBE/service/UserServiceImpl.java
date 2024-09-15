package com.example.hmg.wishlistBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.dto.UserDto;
import com.example.hmg.wishlistBE.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Provides business logic for handling user-related operations
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;


    /**
     * Fetch a User from their username
     */
    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }


    /**
     * Save a new user to DB
     */
    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getName());
        return userRepo.save(user);
    }
}
