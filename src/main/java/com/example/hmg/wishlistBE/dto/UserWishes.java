package com.example.hmg.wishlistBE.dto;

import java.util.List;

public class UserWishes {
    private Long userId;
    private String username;
    private String name;
    private List<String> wishes;

    public UserWishes() {};

    public UserWishes(Long userId, String username, String name, List<String> wishes) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.wishes = wishes;
    }
}
