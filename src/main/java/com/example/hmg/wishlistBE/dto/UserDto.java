package com.example.hmg.wishlistBE.dto;

import java.util.List;

public class UserDto {

    private String username;
    private String password;
    private String name;
    private String friendsUsernames;

    public UserDto(String username, String password, String name, String friendsUsernames) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.friendsUsernames = friendsUsernames;
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

    public void setFriendsUsernames(String friendsUsernames) {
        this.friendsUsernames = friendsUsernames;
    }

    public String getFriendsUsernames() {
        return this.friendsUsernames;
    }

    @Override
    public String toString() {
        return "UserDto [username=" + username + ", password=" + password + ", name=" + name + "]";
    }
}