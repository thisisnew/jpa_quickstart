package com.rubypaper.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.repository.EmployeeRepository;


@Service("empService")
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void insertEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}
	
	public Employee getEmployee(Employee employee) {
		return employeeRepository.findById(employee.getId()).get();
	}
	
	public List<Employee> getEmployeeList(Employee employee) {
		return employeeRepository.findByJPQL(employee.getName());
	}
}