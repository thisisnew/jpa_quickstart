package com.rubypaper.biz.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class ManyToOneBothWayClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		try {
			dataInsert(emf);
			//dataSelect(emf);
			//dataUpdate(emf);
			dataDelete(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}
	
	
	private static void dataInsert(EntityManagerFactory emf) {
	
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Department dept = new Department();
		dept.setName("개발부");
		//em.persist(dept);
		
		
		for(int i=1; i<=5; i++) {
			Employee employee = new Employee();
			employee.setName("직원-" + i);
			employee.setDept(dept);
		}
		
		em.persist(dept);
		
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		Department dept = em.find(Department.class, 1L);
		
		System.out.println("검색된 부서 : " + dept.getName());
		System.out.println("부서에 속한 직원 명단");
		
		for(Employee emp : dept.getEmpList()) {
			System.out.println(emp.getName() + "(" + emp.getDept().getName() + ")");
		}
		
	}
	
	private static void dataUpdate(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Department dept = new Department();
		dept.setName("영업부");
		em.persist(dept);
		
		Employee emp = em.find(Employee.class, 1L);
		emp.setDept(dept);
		
		em.getTransaction().commit();
	}
	
	
	private static void dataDelete(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Department dept = em.find(Department.class, 1L);
		
		List<Employee> empList = dept.getEmpList();
		
		for(Employee emp : empList) {
			emp.standby();
		}
		
		em.remove(dept);
		em.getTransaction().commit();
		
	}
}
