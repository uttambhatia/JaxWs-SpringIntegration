package com.example.jaxws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:ws-binding.xml")
public class JaxwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaxwsApplication.class, args);
	}

}

