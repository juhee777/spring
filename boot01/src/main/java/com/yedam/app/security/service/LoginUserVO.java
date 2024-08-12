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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //권한 extends(제한)
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		return auths;
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
