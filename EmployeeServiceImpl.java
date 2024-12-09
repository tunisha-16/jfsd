package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public String EmployeeRegistration(Employee employee) {
		employeeRepository.save(employee);
		
		return "Registered successfully";
	}

	@Override
	public Employee checkemployeelogin(String eemail, String epwd) {
		return employeeRepository.checkemployeelogin(eemail, epwd);
		 
	}

	@Override
	public String updateemp(Employee e) {
		// TODO Auto-generated method stub
		Employee emp=employeeRepository.findById(e.getId()).get();
		emp.setContact(e.getContact());
		emp.setDateofbirth(e.getDateofbirth());
		emp.setDepartment(e.getDepartment());
		emp.setGender(e.getGender());
		emp.setLocation(e.getLocation());
		emp.setName(e.getName());
		emp.setPassword(e.getPassword());
		emp.setSalary(e.getSalary());
		
		employeeRepository.save(emp);
		
		return "Employee updated successfully";
	}
	@Override
	public List<Employee> displayEmpsByDepartment(String dept)
	{
		return employeeRepository.findByDepartment(dept);
	}

}
