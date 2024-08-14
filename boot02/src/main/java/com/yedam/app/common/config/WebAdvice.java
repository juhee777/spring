package com.yedam.app.common.config;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //모든 컨트롤러에 공통적으로 (적용하고 싶은게 있으면) 사용되는 기능 별도 관리
public class WebAdvice { //예외처리
	//@ExceptionHandler : 특정 예외에 대한 처리 등록
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<String> handleSqlExcpetion() {
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@ModelAttribute("contextPath") //모든 페이지에서 공통으로 사용 (model.addAttribute와 같은 기능)
	public String contextPath(HttpServletRequest req) {
		return req.getContextPath();
	}
}
