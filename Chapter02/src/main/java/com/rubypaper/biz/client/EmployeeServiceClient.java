package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.EmployeeId;

public class EmployeeServiceClient {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			EmployeeId empId = new EmployeeId(1L, "guest123");
			
			Employee emp = new Employee();
			emp.setEmpId(empId);
			emp.setName("둘리");
			
			em.persist(emp);
			
			tx.commit();
			
			EmployeeId empId2 = new EmployeeId(1L, "guest123");
			Employee employee = em.find(Employee.class, empId2);
			System.out.println("검색된 직원 정보------" + employee.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}
}
