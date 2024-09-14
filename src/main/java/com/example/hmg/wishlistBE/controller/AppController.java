package com.example.hmg.wishlistBE.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.hmg.wishlistBE.entity.Wish;
import com.example.hmg.wishlistBE.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AppController {

    @Autowired
    private UserDetailsService userDetailsService;

    private UserService userService;
    private WishService wishService;

    public AppController(UserService userService, WishService wishService) {
        this.userService = userService;
        this.wishService = wishService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/friends";
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

    @GetMapping("/wishlist")
    public String viewWishlist(Model model, Principal principal) {
        // Get the wishes for the current user by finding the usedId and filtering all wishes
        // Get info on the current user (need the userId to find wishes the user has made)
        User user = userService.findByUsername(principal.getName());
        // Grab wishes filtered by userId
        List<Wish> wishes = wishService.fetchWishList(user.getId());
        // Add user and wishes to model to show userinfo, and the users wishes
        model.addAttribute("user", user);
        model.addAttribute("wishes", wishes);
        return "wishlist";
    }

    @PostMapping("/add-wish")
    public String addWish(@RequestParam String newWishName, @RequestParam(required = false) String newWishDescription, Principal principal) {
        System.out.println(("Adding " + newWishName+" for user "+principal.getName()));
        User user = userService.findByUsername(principal.getName());
        Wish newWish = new Wish();
        newWish.setPersonId(user.getId());
        newWish.setWishName(newWishName);
        if (newWishDescription != null) {
            newWish.setWishDescription(newWishDescription);
        }
        wishService.saveWish(newWish);
        return "redirect:/wishlist?success";
    }

    @PostMapping("/add-friend")
    public String addFriend(@RequestParam String newFriendUsername, Principal principal) {
        System.out.println(("Adding friend "+newFriendUsername+" for user "+principal.getName()));
        // Check that friend exists
        User friend = userService.findByUsername(newFriendUsername);
        User user = userService.findByUsername(principal.getName());
        UserDto userDto = new UserDto(user.getUsername(), user.getPassword(), user.getName(), user.getFriendsUsernames());
        String friends = userDto.getFriendsUsernames();
        System.out.println(friends);
        if (friends == null) {
            friends = friend.getUsername();
        } else {
            friends = friends + ", " + friend.getUsername();
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