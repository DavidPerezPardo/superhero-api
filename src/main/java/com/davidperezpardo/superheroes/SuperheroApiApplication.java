package com.davidperezpardo.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SuperheroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperheroApiApplication.class, args);
	}

}
