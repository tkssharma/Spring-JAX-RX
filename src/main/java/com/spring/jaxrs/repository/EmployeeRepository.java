package com.spring.jaxrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jaxrs.entity.Employee;



public interface EmployeeRepository extends  JpaRepository<Employee, Integer> {

}
