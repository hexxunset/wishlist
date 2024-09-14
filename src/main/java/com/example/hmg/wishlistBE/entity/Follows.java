package com.example.hmg.wishlistBE.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class Follows {

    @Id
    private String id;

    @Column(nullable = false)
    private String personUsername;

    @Column(nullable = false)
    private String followsUsername;

    public Follows() {}

    public Follows(String personUsername, String followsUsername) {
        super();
        this.id = personUsername + "_" + followsUsername;
        this.personUsername = personUsername;
        this.followsUsername = followsUsername;
    }

    public void setId(String id) { this.id = id; }
    public void setPersonId(String personUsername) { this.personUsername = personUsername; }
    public void setFollowsId(String followsUsername) { this.followsUsername = followsUsername; }

    public String getId() { return this.id; }
    public String getPersonUsername() { return this.personUsername; }
    public String getFollowsUsername() { return this.followsUsername; }
}
