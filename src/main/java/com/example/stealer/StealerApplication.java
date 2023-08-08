package com.example.stealer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.stealer.repo")
public class StealerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StealerApplication.class, args);
	}

}
