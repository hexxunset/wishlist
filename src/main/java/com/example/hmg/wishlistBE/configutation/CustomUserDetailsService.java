package com.example.hmg.wishlistBE.configutation;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.repository.UserRepository;

/**
 * CustomUserDetailsService, used by Spring Security to look-up a CustomUserDetail during authentication process
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        System.out.println("User: ");
        System.out.println(user.getName());
        System.out.println(user.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username or Password not found");
        }
        return new CustomUserDetails(user.getUsername(), user.getPassword(), authorities(), user.getName());
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

}