package com.webscraper.api.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigDrivers {
	
	@Value("${chrome.driverpath}")
	private String driverLocation;

	public WebDriver getWebDriver() {
		ChromeDriverService driverService = this.getExecutableDriver();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless","--window-size=1920,1200");
		
		WebDriver driver = new ChromeDriver(driverService,options);
		return driver;
	}
	
	private ChromeDriverService getExecutableDriver() {
		return new ChromeDriverService.Builder().usingDriverExecutable(new File(driverLocation)).build();
	}
}
