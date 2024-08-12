package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserVO implements UserDetails{
	
	private UserVO userVO;
	
	@Override       //GrantedAuthority 인터페이스를 구현한 어떤 타입이든 가능 /getAuthorities : 메서드 이름, 사용자의 권한 목록을 반환하는 역할
	public Collection<? extends GrantedAuthority> getAuthorities() { //GrantedAuthority: 스프링 시큐리티에서 권한을 나타내는 인터페이스
		List<GrantedAuthority> auths = new ArrayList<>(); //GrantedAuthority 객체들을 담을 수 있는 새로운 ArrayList를 생성
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName())); //새 SimpleGrantedAuthority 객체를 생성하고 리스트에 추가
		return auths; //생성된 권한 목록을 반환
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() { //계정만료여부 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //계정잠금여부
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //계정 패스워드 만료여부
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 사용여부
		return true;
	}
	
}
