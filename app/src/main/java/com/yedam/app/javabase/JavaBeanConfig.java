package com.yedam.app.javabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBeanConfig {
	@Bean //메서드를 실행을 빈으로 등록
	public Chef chef() {
		return new Chef();
	}
	
	@Bean
	public Restaurant restaurant(Chef chef) {
		return new Restaurant(chef);
	}
}
