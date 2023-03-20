package com.biaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BiafApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiafApplication.class, args);
	}

}
