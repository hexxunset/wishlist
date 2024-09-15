package com.example.hmg.wishlistBE.controller;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.entity.Wish;
import com.example.hmg.wishlistBE.service.UserService;
import com.example.hmg.wishlistBE.service.WishService;
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
    public  List<Wish> restGetWishList() {
        // Grab all wishes
        return wishService.fetchWishList();
    }

//    @PostMapping("/rest/buy-wish")
//    public Wish restBuyWish(@RequestBody String wishId) {
//        System.out.println("Welcome to rest buy wish");
//        return wishService.incrementWishBoughtNumber(Long.valueOf(wishId));
//    }
}