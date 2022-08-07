package com.lawencon.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@ComponentScan({"com.lawencon"})
@EntityScan({"com.lawencon"})
@EnableGlobalMethodSecurity(
		prePostEnabled = true
)
public class CommunityApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}
}
