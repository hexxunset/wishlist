package com.example.hmg.wishlistBE.controller;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.entity.Wish;
import com.example.hmg.wishlistBE.service.UserService;
import com.example.hmg.wishlistBE.service.WishService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

// Restcontroller combines Controller and ResponseBody
// Allows us to return String instead of a full view
@RestController
public class RestAppController {
    private UserService userService;
    private WishService wishService;

    public RestAppController(UserService userService, WishService wishService) {
        this.userService = userService;
        this.wishService = wishService;
    }

    @GetMapping("/rest/wishlist")
    public  List<Wish> getRestWishList(Principal principal) {
        // Get the wishes for the current user by finding the usedId and filtering all wishes
        // Get info on the current user (need the userId to find wishes the user has made)
        User user = userService.findByUsername(principal.getName());
        // Grab wishes filtered by userId
        return wishService.fetchWishList(user.getId());
    }
}