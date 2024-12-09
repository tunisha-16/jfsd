package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Employee;

public interface EmployeeService {
	public String EmployeeRegistration(Employee e);
	public Employee checkemployeelogin(String eemail,String epwd);
	public String updateemp(Employee e);
	
	public List<Employee> displayEmpsByDepartment(String dept);
}


