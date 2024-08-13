package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Bean //비밀번호 암호화
	PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	
	//인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //Security가 적용될 URI
		http.authorizeHttpRequests((authrize) 
				-> authrize
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //permitAll 인증받지 않은 모든 대상 오픈
				.requestMatchers("/", "/all").permitAll()
				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated() //anyRequest else
				)
				.formLogin(formlogin -> formlogin
						.defaultSuccessUrl("/all"))
				.logout(logout -> logout
						.logoutSuccessUrl("/all")
						.invalidateHttpSession(true));
		   
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
	
	/*
	@Bean //메모리상 인증정보 등록 => 테스트 전용 방식
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
								.username("user1")
						.password(passwordEncoder().encode("1234"))
							.roles("USER") //ROLE_USER
							//.authorities("ROLE_USER")
							.build();
		
		UserDetails admin = User.builder()
								.username("admin1")
						.password(passwordEncoder().encode("1234"))
								//.roles("ADMIN") //ROLE_ADMIN
								.authorities("ROLE_ADMIN", "ROLE_USER") //권한 동시 부여
								.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	*/
}
