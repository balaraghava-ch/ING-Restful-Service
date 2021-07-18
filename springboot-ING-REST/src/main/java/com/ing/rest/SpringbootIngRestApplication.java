package com.ing.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringbootIngRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIngRestApplication.class, args);
	}
    
	@Bean
	public RestTemplate getRestTemplate()
    {
    	return new RestTemplate();
    }
	

}
