package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class ManyToOneOneWayClient {
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
		em.persist(dept);
		
		Employee emp1 = new Employee();
		emp1.setName("둘리");
		emp1.setDept(dept);
		em.persist(emp1);
		
		Employee emp2 = new Employee();
		emp2.setName("도우너");
		emp2.setDept(dept);
		em.persist(emp2);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		Employee employee = em.find(Employee.class, 2L);
		//System.out.println(employee.getName() + " 직원이 검색됨");
		System.out.println(employee.getName() + "의 부서 : " + employee.getDept().getName());
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
		
		Employee emp1 = em.find(Employee.class, 1L);
		emp1.setDept(null);
		
		Employee emp2 = em.find(Employee.class, 2L);
		emp2.setDept(null);
		
		Department dept = em.find(Department.class, 1L);
		em.remove(dept);
		em.getTransaction().commit();
		
	}
}
