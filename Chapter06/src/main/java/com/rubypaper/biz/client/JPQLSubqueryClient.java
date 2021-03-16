package com.rubypaper.biz.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class JPQLSubqueryClient {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter06");
		
		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
		
	}
	
	static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		String jpql = "SELECT e FROM Employee e JOIN FETCH e.dept "
					+ "WHERE e.salary > (SELECT AVG(e.salary) "
					+ "					FROM Employee e)";
		
		TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
		
		List<Employee> list = query.getResultList();
		
		System.out.println("평균 이상의 수급자 목록");
		
		for(Employee res : list) {
			System.out.println(res.getName());
		}
		
		em.close();
	}
	
	static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Department dep1 = new Department();
		dep1.setName("개발부");
		
		for(int i=1; i<=3; i++) {
			Employee emp = new Employee();
			
			emp.setName("개발직원" + i);
			emp.setSalary(i * 12700.00);
			emp.setMailId("Dev-" + i);
			emp.setDept(dep1);
		}
		
		em.persist(dep1);
		
		Department dep2 = new Department();
		dep1.setName("영업부");
		
		for(int i=1; i<=3; i++) {
			Employee emp = new Employee();
			
			emp.setName("영업직원" + i);
			emp.setSalary(i * 27300.00);
			emp.setMailId("Sale-" + i);
			emp.setDept(dep2);
		}
		
		em.persist(dep2);
		
		Department dep3 = new Department();
		dep3.setName("인재개발부");
		em.persist(dep3);
		
		Employee emp = new Employee();
		emp.setName("아르바이트");
		emp.setMailId("Alba-01");
		emp.setSalary(10000.00);
		em.persist(emp);
		
		em.getTransaction().commit();
		em.close();
	}
}
