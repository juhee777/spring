package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //Bean 등록, Web과 관련된 부분
public class URLController {
	//@RequestMapping(path="/test", method=RequestMethod.GET)
	@GetMapping("/test")
	@ResponseBody 
	public String urlGetTest(String keyword) { // /test?keyword=hello  keyword라는 파라미터를 받음
		return "Server Reponse : Get Method\n Select - " + keyword; //응답확인
	}
	
	//@RequestMapping(path="/test", method=RequestMethod.POST)
	@PostMapping("/test")
	@ResponseBody 
	public String urlPostTest(String keyword) { 
		return "Server Reponse : Post Method\n Select - " + keyword; //응답확인
	}
}
