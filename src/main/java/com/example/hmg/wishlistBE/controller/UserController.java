package com.example.hmg.wishlistBE.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/friends";
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

    @GetMapping("/friends")
    public String listUsers(Model model, Principal principal) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        //UserDetails friendDetails = userDetailsService.loadUserByUsername(userDetails.get)
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        System.out.println(user);
        System.out.println(user.getFriendsUsernames());
        String[] friendsArr = {"No friends added"};
        if (user.getFriendsUsernames() != null) {
            friendsArr = user.getFriendsUsernames().split(", ");
        }
        Set<String> friends = new HashSet<>(Arrays.asList(friendsArr));
        System.out.println(friends);
        model.addAttribute("friends", friends);
        return "friends";
    }

    @PostMapping("/add-friend")
    public String addFriend(@RequestParam String newFriend, Principal principal) {
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
        return "redirect:/friends?success";
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