package com.spring.jaxrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaxrs.entity.Employee;
import com.spring.jaxrs.repository.EmployeeRepository;


@Service
public class EmployeeService {


	@Autowired
	public EmployeeRepository empRepo;

	public void save(Employee emp) {
		empRepo.save(emp);

	}

	public Employee getEmpDate(int empId) {
		// TODO Auto-generated method stub
		return empRepo.findOne(empId);
	}

	public void Delete(int empId) {
		empRepo.delete(empId);
	}


}
