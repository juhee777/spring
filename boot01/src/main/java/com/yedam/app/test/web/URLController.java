package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //Bean 등록, Web과 관련된 부분
public class URLController {
	//@RequestMapping(path="/test", method=RequestMethod.GET)
	@GetMapping("/test")
	@ResponseBody
	public String urlGetTest(String keyword) {
		return "Server Reponse : Get Method\n Select - " + keyword; //응답확인
	}
}
