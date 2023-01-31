package org.generation.ecommercedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.generation.ecommercedb.config.JwtFilter;

@SpringBootApplication
public class EcommercedbApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercedbApplication.class, args);
	}//main

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter(){
		FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/productos/*");
		registrationBean.addUrlPatterns("/api/usuarios/*");
		return registrationBean;
	}//jwtFilter
	
	
}//class EcommercedbApplication
