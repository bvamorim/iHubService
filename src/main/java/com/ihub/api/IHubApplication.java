package com.ihub.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ihub.api.services.DataInitializer;

/**
* <h1>IHubApplication</h1>
* The main class, is required to run the Application.
*
* @author  Bruno Amorim
* @version 1.0
* @since   2021-05-29
*/
@SpringBootApplication
public class IHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(IHubApplication.class, args);
	}
	
	@Bean
	public String initializeData(DataInitializer dataInitializer) {
		dataInitializer.createAdmin();
		
		return "";
	}
	
}
