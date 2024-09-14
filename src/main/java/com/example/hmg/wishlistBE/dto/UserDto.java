package com.example.hmg.wishlistBE.dto;

public class UserDto {

    private String username;
    private String password;
    private String name;
    private UserWishes friendsWishes;

    public UserDto(String username, String password, String name, UserWishes friendsWishes) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.friendsWishes = friendsWishes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriendsWishes(UserWishes friendsWishes) {
        this.friendsWishes = friendsWishes;
    }

    public UserWishes getFriendsWishes() {
        return this.friendsWishes;
    }

    @Override
    public String toString() {
        return "UserDto [username=" + username + ", password=" + password + ", name=" + name + "]";
    }
}