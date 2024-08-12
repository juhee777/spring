package com.yedam.app.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.app.security.service.LoginUserVO;
import com.yedam.app.security.service.UserVO;

@Service
public class CustomerUserDetailsService implements UserDetailsService { //UserDetailsService 사용자 인증 처리
	private UserMapper userMapper;
	
	public CustomerUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Mapper를 활용해서 DB에 접근
		UserVO userVO = userMapper.getUserInfo(username);
		
		if(userVO == null) { //사용자 정보가 없을 경우 어떤 사용자 이름이 없는지 확인
			throw new UsernameNotFoundException(username);
		}
		
		return new LoginUserVO(userVO);
	}

}
