package com.example.hmg.wishlistBE.service;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.dto.UserDto;
import org.springframework.stereotype.Service;

public interface UserService {
    /**
     * Finds a user
     * @param username the username for user to fin
     * @return ??
     */
    User findByUsername(String username);

    /**
     * Saves a user entity.
     * @param user the user to save
     * @return the saved user
     */
    User save(UserDto userDto);

    User update(Long userId, UserDto userDto);

}