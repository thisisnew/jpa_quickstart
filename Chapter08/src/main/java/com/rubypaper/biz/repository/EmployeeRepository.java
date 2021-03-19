package com.rubypaper.biz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rubypaper.biz.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
	List<Employee> findByName(String name);
	
	Page<Employee> findByNameContaining(String name, Pageable paging);
	
	List<Employee> findByNameContainingOrMailIdContaining(String name, String mailId);
	
	List<Employee> findByMailIdContainingOrderByNameDesc(String mailId);
	
	@Query("SELECT emp FROM Employee emp WHERE emp.name like %?1%")
	List<Employee> findByJPQL(String name);
}
