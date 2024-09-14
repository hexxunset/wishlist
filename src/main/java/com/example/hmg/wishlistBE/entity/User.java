package com.example.hmg.wishlistBE.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column
    private String friendsUsernames;

    public User() {}

    public User(String username, String password, String name) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriendsUsernames(String friendsUsernames) {
        this.friendsUsernames = friendsUsernames;
    }

    public Long getId() { return this.id; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getName() {return this.name; }
    public String getFriendsUsernames() {return this.friendsUsernames; }
}
