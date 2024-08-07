package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service
//@AllArgsConstructor 
public class EmpServiceEmpl implements EmpService{
	
	public EmpMapper empMapper;
	
	@Autowired
	EmpServiceEmpl(EmpMapper empMapper){ //@AllArgsConstructor쓰면 필요 없음
		this.empMapper = empMapper;
	}
	
	@Override
	public List<EmpVO> empList() {
		// TODO Auto-generated method stub
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		// TODO Auto-generated method stub
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		// TODO Auto-generated method stub
		int result = empMapper.insertEmpInfo(empVO);
		
		return result == 1 ? empVO.getEmployeeId() : -1; 
	}

	@Override
	public Map<String, Object> empUdate(EmpVO empVO) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", empVO);
		
		return map;
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		
		int result = empMapper.deleteEmpInfo(empId);
		
		if(result == 1) {
			map.put("employeeId", empId);
		}
		//{}
		//{"employeeId" : 104}
		return map;
	}

}
