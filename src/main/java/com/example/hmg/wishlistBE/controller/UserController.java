package com.example.hmg.wishlistBE.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.service.UserService;
import com.example.hmg.wishlistBE.dto.UserDto;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, UserDto userDto) {

        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "register";
    }

    @GetMapping("/users")
    public String listUsers(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        //UserDetails friendDetails = userDetailsService.loadUserByUsername(userDetails.get)
        model.addAttribute("userdetail", userDetails);
        System.out.println(userDetails);
        return "users";
    }

    @PostMapping("/add-friend")
    public String addFriend(Principal principal) {
        String newFriend = "test1";
        System.out.println(("Adding friend "+newFriend+" for user "+principal.getName()));
        User user = userService.findByUsername(principal.getName());
        UserDto userDto = new UserDto(user.getUsername(), user.getPassword(), user.getName(), user.getFriendsUsernames());
        String friends = userDto.getFriendsUsernames();
        System.out.println(friends);
        if (friends == null) {
            friends = newFriend;
        } else {
            friends = friends + ", " + newFriend;
        }
        System.out.println(friends);
        userDto.setFriendsUsernames(friends);
        userService.update(user.getId(), userDto);
        return "friend_added";
    }

    @PostMapping("/register")
    public String registerSava(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            model.addAttribute("Userexist", user);
            return "register";
        }
        userService.save(userDto);
        return "redirect:/register?success";
    }
}