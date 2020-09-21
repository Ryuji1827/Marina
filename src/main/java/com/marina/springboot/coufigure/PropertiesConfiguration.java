package com.marina.springboot.coufigure;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {
	
	@Bean
	public ErrorContext errorContext() {
		return new ErrorContext();
	}
}
