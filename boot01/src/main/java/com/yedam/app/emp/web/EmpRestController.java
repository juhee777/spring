package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@RestController // @Controller + 모든 메소드에 @ResponseBody를 적용
				// @ResponseBody : AJAX
public class EmpRestController {
	private EmpService empService;
	
	@Autowired
	EmpRestController(EmpService empService){
		this.empService = empService;
	}
	
	//전체조회 : GET => emps
	@GetMapping("emps")
	public List<EmpVO> empList(){
		return empService.empList();
	}
	//단건조회 : GET => emps/100 
	@GetMapping("emps/{eid}")
	public EmpVO empInfo(@PathVariable(name = "eid") Integer employeeId) { //Path(경로)Variable(변수) 경로에 붙은 변수값
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		return empService.empInfo(empVO);
	}
	
	//등록    : POST => emps
	@PostMapping("emps") //@RequestBody : JSON으로 데이터를 받음
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}
	
	//수정	 : PUT => emps/100
	@PutMapping("emps/{employeeId}") //@RequestBody : JSON으로 데이터를 받음
	public Map<String, Object> empUpdate(
										 //경로를 통해서 수정할 Target(대상)
										 @PathVariable Integer employeeId,
										 //수정할 정보는 JSON 포맷으로
										 @RequestBody EmpVO empVO) {
		empVO.setEmployeeId(employeeId);
		return empService.empUpdate(empVO);
	}
	
	//삭제	 : DELETE => emps/100
	@DeleteMapping("emps/{employeeId}")
	public Map<String, Object> empDelete(@PathVariable Integer employeeId){
		return empService.empDelete(employeeId);
	}
}
