package com.leonlib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "com.leonlib.model",
    "com.leonlib.repository",
    "com.leonlib.config",
    "com.leonlib.controller",
    "com.leonlib.db",
    "com.leonlib.service"
})
@EnableJpaRepositories("com.leonlib.repository")
public class LeonlibApplication {

	public static void main(final String[] args) {
		SpringApplication.run(LeonlibApplication.class, args);
	}

}
