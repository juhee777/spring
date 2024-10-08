package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;
@CrossOrigin(origins = "*")
@Controller
public class ParamConteroller {
	//QueryString(질의문자열) : key=value&key=value&...
	//Content-type : application/x-www-form-urlencode 
	//Method : 상관없음
	
	//QueryString => 커맨드 객체 (어노테이션 X) : 객체
	@RequestMapping(path="comobj", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	//QueryString => @RequestParam : 기본타입, 단일값
	@RequestMapping(path="reqparm", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam
		    (@RequestParam Integer employeeId, //필수
		    			   String lastName,    //@RequestParam 없으면 생략가능
		     @RequestParam(name="message", //파라미터 이름
		    		 	   defaultValue="No message",
		    		 	   required = true) String msg ) {
		String result = "";
		result += "Path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}
	
	// @PathVariable : 경로에 값을 포함하는 방식, 단일 값
	// Method는 상관없음
	// Content-type도 상관없음
	@RequestMapping("path/{id}") // path/Hong, path/1234
	@ResponseBody
	public String pathVariable(@PathVariable String id) { //PathVariable는 값이 누락되면 404
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	
	//@RequestBody : JSON 포맷, 배열 or 객체
	//Method : POST, PUT
	//Content-Type : application/json
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) { // 사용자가 보낸 JSON 데이터가 empVO 객체로 변환
		String result = "Path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) { //[{}]
		String result = "Path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		return result;
	}

	
}
