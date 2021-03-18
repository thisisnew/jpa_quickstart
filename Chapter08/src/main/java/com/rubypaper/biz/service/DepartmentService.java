package com.rubypaper.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.persistence.DepartmentRepository;

@Service("deptService")
@Transactional
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository deptRepository;
	
	public void insertDepartment(Department department) {
		deptRepository.insertDepartment(department);
	}
	
	public Department getDepartment(Department department) {
		return deptRepository.getDepartment(department);
	}
	
}