package com.example.hmg.wishlistBE.dto;

import com.example.hmg.wishlistBE.entity.Wish;

import java.util.ArrayList;
import java.util.List;

public class UserWishes {
    private Long userId;
    private String username;
    private String name;
    private List<Wish> wishes;

    public UserWishes() {};

    public UserWishes(Long userId, String username, String name, List<Wish> wishes) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.wishes = wishes;
    }

    public String getUsername() { return this.username; }
    public String getName() { return this.name; }
    public List<Wish> getWishes() { return this.wishes; }

    @Override
    public String toString() {
        return "UserWish for user " + this.username;
    }
}
