package com.campusdrive.uniride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration.class })
public class UnirideApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnirideApplication.class, args);
	}

}
