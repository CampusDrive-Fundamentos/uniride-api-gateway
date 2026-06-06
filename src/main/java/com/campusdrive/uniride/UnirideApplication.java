package com.campusdrive.uniride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(excludeName = {
		"org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration",
		"org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration",
		"org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration",
		"org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration"
})
public class UnirideApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnirideApplication.class, args);
	}

}
