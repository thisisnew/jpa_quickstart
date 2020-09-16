package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;

public class EmployeeServiceClient {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03"); //xml에 설정된 persistence-unit
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
//			Employee employee = new Employee();
//			employee.setName("둘리");
//			
//			tx.begin();
//			
//			em.persist(employee);
//			tx.commit();
			
			Employee findEmp = em.find(Employee.class, 1L);
			System.out.println("검색된 직원 정보:" + findEmp.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}
}
