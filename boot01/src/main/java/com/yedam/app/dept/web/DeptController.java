package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor 
public class DeptController {
	private DeptService deptService;
	
	//전체조회
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.deptList();
		model.addAttribute("depts", list);
		return "dept/list";
	}
	
	//단건조회
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept",findVO);
		return "dept/info";
	}
	
	//등록 - 페이지
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert";
	}
	
	//등록 - 처리
	@PostMapping("deptInsert")
	public String deptInsertProcess(DeptVO deptVO) {
		int did = deptService.deptInsert(deptVO);
		String url = null;
		if(did > -1) {
			url = "redirect:deptInfo?departmentId="+did;
		}else {
			url = "redirect:deptList";
		}
		return url;
	}
	
	//수정 - 페이지
	@GetMapping("deptUpdate")
	public String deptUpdateForm(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept",findVO);
		return "dept/update";
	}
	
	//수정 - 처리
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object> deptupdateAJAXQueryString(DeptVO deptVO){
		return deptService.deptUpdate(deptVO);
	}
	
	//삭제 - 처리
	@GetMapping("deptDelete")
	public String deptDelete(Integer departmentId) {
		deptService.deptDelete(departmentId);
		return "redirect:deptList";
	}
}
