package com.rubypaper.biz.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;

public class EmployeeServiceClient {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("Chapter03");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			
			tx.begin();
			
			for(int i=2; i<=10; i++) {
				Employee emp = new Employee();
				emp.setName("직원-" + i);
				em.persist(emp);
			}

			tx.commit();
			
			String jqpl = "SELECT e FROM Employee e ORDER BY e.id DESC";
			List<Employee> list = em.createQuery(jqpl, Employee.class).getResultList();
			
			for(Employee employee : list) {
				System.out.println("------>" + employee.toString());
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

