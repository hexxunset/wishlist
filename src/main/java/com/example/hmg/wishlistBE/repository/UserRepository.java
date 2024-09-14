package com.example.hmg.wishlistBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.dto.UserDto;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
