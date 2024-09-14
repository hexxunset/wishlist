package com.example.hmg.wishlistBE.controller;

import java.security.Principal;
import java.util.*;

import com.example.hmg.wishlistBE.dto.UserWishes;
import com.example.hmg.wishlistBE.entity.Follows;
import com.example.hmg.wishlistBE.entity.Wish;
import com.example.hmg.wishlistBE.service.FollowsService;
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
    private FollowsService followsService;

    public AppController(UserService userService, WishService wishService, FollowsService followsService) {
        this.userService = userService;
        this.wishService = wishService;
        this.followsService = followsService;
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
        // Get user from the userservice, and add it as an attribute to the model
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        System.out.println(user);
        // Fetch who the user follows, and add it as an attribute to the model
        List<Follows> userFollows = followsService.fetchFollowsList(user.getUsername());
        System.out.println(userFollows);
        // Fetch wishes made by everyone the user follows
        List<UserWishes> friendsWishes = new ArrayList<>();
        for (Follows row : userFollows) {
            User friend = userService.findByUsername(row.getFollowsUsername());
            List<Wish> friendWishes = wishService.fetchWishList(friend.getId());
            System.out.println("grabbed wishes friend " + friend.getName() + " has made");
            System.out.println(friendWishes);
            UserWishes friendWishesStructured = new UserWishes(friend.getId(), friend.getUsername(), friend.getName(), friendWishes);
            friendsWishes.add(friendWishesStructured);
        }
        System.out.println("Fetched wishes for all of the users friends");
        if (!friendsWishes.isEmpty()) {
            System.out.println(friendsWishes);
        }
        model.addAttribute("friendsWishes", friendsWishes);
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

    @PostMapping("/buy-wish")
    public String buyWish(@RequestParam String wishId) {
        wishService.incrementWishBoughtNumber(Long.valueOf(wishId));
        return "redirect:/friends?success";
    }

    @PostMapping("/add-friend")
    public String addFriend(@RequestParam String newFriendUsername, Principal principal) {
        // TODO: make sure friend isn't added twice
        System.out.println(("Adding friend "+newFriendUsername+" for user "+principal.getName()));
        // Get user and friend details
        User user = userService.findByUsername(principal.getName());
        User friend = userService.findByUsername(newFriendUsername);
        // Create follows-object and save to DB
        Follows follows = new Follows(user.getUsername(), friend.getUsername());
        followsService.saveFollows(follows);
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