package com.example.hmg.wishlistBE.configutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Allow anyone to access register and login page, so they can sign up/sign in to the application
     * Allow anyone to access REST api while it's under development
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/rest/wishlist").permitAll()
                        .requestMatchers("/rest/buy-wish").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(login ->
                login.usernameParameter("username")
                        .loginPage("/login")
                        .defaultSuccessUrl("/friends")
                        .permitAll()
        ).logout(logout -> logout.logoutSuccessUrl("/").permitAll()
        );

        return http.build();
    }

    /*
    * Use Custom User Details for authentication (default Spring Security is temp User Details)
    */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

    }
}