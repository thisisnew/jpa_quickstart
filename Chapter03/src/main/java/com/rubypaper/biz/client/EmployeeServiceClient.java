package com.rubypaper.biz.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;

public class EmployeeServiceClient {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03"); //xml에 설정된 persistence-unit
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			for(int i = 1; i<=10; i++) {
				Employee emp = new Employee();
				emp.setName("직원-"  + i);
				em.persist(emp);
			}
			
			tx.commit();
			
			String jpql  = "SELECT e FROM Employee e ORDER BY e.id DESC";
			List<Employee> empList = em.createQuery(jpql, Employee.class).getResultList();
			
			for(Employee emp :  empList) {
				System.out.println("------>" +emp.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}
}
