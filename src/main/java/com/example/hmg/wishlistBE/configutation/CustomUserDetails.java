package com.example.hmg.wishlistBE.configutation;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String name;
    private String friendsUsernames;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             String name, String friendsUsernames) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.name = name;
        this.friendsUsernames = friendsUsernames;
    }

    public String getName() {
        return name;
    }

    public String getFriendsUsernames() {
        return friendsUsernames;
    }

    public void setFriendsUsernames(String friendsUsernames) {
        this.friendsUsernames = friendsUsernames;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}