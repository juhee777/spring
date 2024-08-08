package com.yedam.app.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebContrillerAdvice {

	@ModelAttribute("contextPath")//contextPath(전역변수)
	public String contextPath(final HttpServletRequest request) { //HttpServletRequest가 가지고 있는 getContextPath 호출
		return request.getContextPath(); //request만 따로 불러내서 getContextPath
	}
	
}
