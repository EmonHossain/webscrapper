package com.webscraper.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebscraperApplication implements CommandLineRunner{

	@Value("${chrome.driverpath}")
	private String driverLocation;
	
	public static void main(String[] args) {
		
		SpringApplication.run(WebscraperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.setProperty("webdriver.chrome.driver", driverLocation);
	}
}
