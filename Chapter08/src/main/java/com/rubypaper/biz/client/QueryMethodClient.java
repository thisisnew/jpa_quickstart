package com.rubypaper.biz.client;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;

import com.rubypaper.biz.config.SpringConfiguration;
import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.service.DepartmentService;
import com.rubypaper.biz.service.EmployeeService;

public class QueryMethodClient {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		
		DepartmentService deptService = (DepartmentService) container.getBean("deptService");
		EmployeeService empService = (EmployeeService) container.getBean("empService");
		
		dataInsert(deptService, empService);
		dataSelect(empService);
	}
	
	private static void dataSelect(EmployeeService employeeService) {
		Employee employee = new Employee();
		employee.setName("개발");
		
		List<Employee> resultList = employeeService.getEmployeeList(employee);
		System.out.println("\n[검색된 회원 목록]");
		
		for(Employee emp : resultList) {
			System.out.println("---->" + emp.toString());
		}
	}
	
	private static void dataInsert(DepartmentService deptService, EmployeeService employeeService) {
		
		Department department1 = new Department();
		department1.setName("개발부");
		deptService.insertDepartment(department1);
		
		Department department2 = new Department();
		department1.setName("영업부");
		deptService.insertDepartment(department2);
		
		for(int i=1; i<=5; i++) {
			Employee employee = new Employee();
			employee.setName("개발직원" + i);
			employee.setSalary(i * 12700.00);
			employee.setMailId("DEV - " + i);
			employee.setDept(department1);
			employeeService.insertEmployee(employee);
		}
		
		for(int i=1; i<=7; i++) {
			Employee employee = new Employee();
			employee.setName("영업직원" + i);
			employee.setSalary(i * 24300.00);
			employee.setMailId("Sales - " + i);
			employee.setDept(department2);
			employeeService.insertEmployee(employee);
		}
		
	}
}
