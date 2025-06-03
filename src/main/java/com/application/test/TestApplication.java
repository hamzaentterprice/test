package com.application.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	@Bean
	public CommandLineRunner displaySecretAtStartup(@Value("${my.secret:Secret not set}") String mySecret) {
		return args -> {
			System.out.println("========================================");
			System.out.println("TEST MODE - DISPLAYING SECRET VALUE:");
			System.out.println("MY_GITHUB_SECRET = " + mySecret);
			System.out.println("========================================");
		};
	}
}
