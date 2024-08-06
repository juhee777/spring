package com.yedam.app.emp.service;

import java.sql.Date;

import lombok.Data;

@Data
public class EmpVO {
	//employee_id => employeeId
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
	private int departmentId;
	
	
}
