package com.example.hmg.wishlistBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.dto.UserDto;
import com.example.hmg.wishlistBE.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getName());
        return userRepo.save(user);
    }

    @Override
    public User update(Long userId, UserDto userDto) {
        User userDB = userRepo.getReferenceById(userId);
        System.out.println("welcome to update user");
        // Updates fields if they are not null or empty.
        // Currently only friends-field is available to update
//        if (Objects.nonNull(userDto.getUsername()) && !"".equalsIgnoreCase(userDB.getUsername())) {
//            userDB.setUsername(userDto.getUsername());
//        }
//        if (Objects.nonNull(userDto.getName()) && !"".equalsIgnoreCase(userDB.getName())) {
//            userDB.setName(userDto.getName());
//        }
//        if (Objects.nonNull(userDto.getFriendsWishes()) && !"".equalsIgnoreCase(userDB.getFriendsUsernames())) {
//            userDB.setFriendsUsernames(userDto.getFriendsWishes());
//        }
        return userRepo.save(userDB);
    }
}
