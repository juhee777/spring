package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller //route : 사용자의 요청(endpoint)와 그에 대한 처리
			 // : URL + METHOD => Service => View
public class EmpController {
	//해당 컨드롤러에 제공하는 서비스
	private EmpService empService;
	
	@Autowired //@Autowired을 사용해서 String이 자동으로 의존성을 주입
	public EmpController(EmpService empService) {
		this.empService = empService;
	}
	
	// GET : 조회, 빈페이지 (보안안됨)
	// POST : 데이터 조작(등록, 수정, 삭제)
	
	// 전체조회 : GET
	@GetMapping("empList") //Model = Request + Response
	public String empList(Model model) {
		// 1) 해당 기능 수행 => Service
		List<EmpVO> list = empService.empList();
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list); //list가 "emps"라는 이름으로 모델객체에 저장, "emps"라는 이름으로 데이터에 접근할 수 있음
		return "emp/list"; // 3) 데이터를 출력할 페이지 결정 emp/list(파일)
		// classpath:/templates/  emp/list .html
		// prdfix                 return    subfix
	}
	
	// 단건조회 : Get => QueryString
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		// 1) 해당 기능 수행 => Service
		EmpVO findVO = empService.empInfo(empVO);
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emp", findVO); 
		return "emp/info"; // 3) 데이터를 출력할 페이지 결정 
		// classpath:/templates/ emp/info .html
		// prdfix                return    subfix
		//return "redirect:empList"; //redirect를 붙이면 새로운 경로가 발생
	}
	
	// 등록 - 페이지 : GET
	@GetMapping("empInsert") //등록 폼을 보여주기 위한 GET 요청
	public String empInsertForm() {
		return "emp/insert";
	}
	
	// 등록 - 처리 : POST => form태그를 통한 submit
	@PostMapping("empInsert") //실제 등록 처리를 위한 POST 요청
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		String url = null;
		if(eid > -1) {
			//정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId="+eid;
		}else {
			//등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	}
	
	// 수정 - 페이지 : Get <=> 단건조회
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}
	
	// 수정 - 처리 : AJAX => QueryString (java에서 기본적으로 하는 방식)
	//@PostMapping("empUpdate")
	@ResponseBody
	public Map<String, Object> empupdateAJAXQueryString(EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	// 수정 - 처리 : AJAX => JSON
	@PostMapping("empUpdate")
	@ResponseBody
	public Map<String, Object> empupdateAJAXJSON(@RequestBody EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	// 삭제 - 처리 : GET
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList"; //현재테이터로 실시간 조회
	}
	
}
