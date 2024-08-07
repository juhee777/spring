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
		this.empMapper = empMapper; //this.empMapper는 외부에서 전달된 EmpMapper객체를 사용할 수 있음
	}
	
	@Override
	public List<EmpVO> empList() {
		
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		
		int result = empMapper.insertEmpInfo(empVO); // EmpMapper 인터페이스의 insertEmpInfo 메서드를 호출하여 empVO의 정보를 데이터베이스에 삽입
		
		return result == 1 ? empVO.getEmployeeId() : -1; //삽입이 성공한 경우 empVO.getEmployeeId(), 안될경우 -1
	}

	@Override
	public Map<String, Object> empUdate(EmpVO empVO) {
		
		Map<String, Object> map = new HashMap<>(); //결과를 저장할 Map 객체를 생성
		
		boolean isSuccessed = false; //업데이트 성공 여부, 기본값은 false
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		// empMapper의 updateEmpInfo 메서드를 호출하여 id를 기준으로 empVO에서 새로운 값가져와 업데이트
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed); //업데이트 성공 여부를 map에 저장
		map.put("target", empVO); //업데이트된 직원 정보(empVO)를 map에 저장
		
		return map;
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		
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
