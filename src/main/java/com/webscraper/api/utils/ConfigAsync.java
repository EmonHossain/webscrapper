package com.webscraper.api.utils;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync 
public class ConfigAsync {

	@Bean("taskExecutor")
	public Executor taskExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(16);
        executor.setMaxPoolSize(16);
        //executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("ScraperThread-");
        executor.initialize();
        return executor;
	}
	
}
