package com.example.hmg.wishlistBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
public class WishlistBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishlistBeApplication.class, args);
	}

}
