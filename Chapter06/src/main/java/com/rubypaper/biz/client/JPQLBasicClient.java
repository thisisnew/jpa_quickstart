package com.rubypaper.biz.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.EmployeeSalaryData;

public class JPQLBasicClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("Chapter06");
		
		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}
	
	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		String jpql = "SELECT id, name, title, deptName, salary " +
					"FROM Employee WHERE id = :employeeId AND name = :employeeName";
					
		Query query = em.createQuery(jpql);
		query.setParameter("employeeId", 1L);
		query.setParameter("employeeName", "직원 1");
		
		Object[] result =  (Object[]) query.getSingleResult();
		
		System.out.println(result[0] + "번 직원 정보");
		
		System.out.println(Arrays.toString(result));
		
		em.close();
	}
	
	private static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		for(int i=1; i<=10; i++) {
			Employee emp = new Employee();
			emp.setName("직원 " + i);
			emp.setMailId("anti-corona" + i);
			emp.setDeptName("개발부");
			emp.setSalary(12700.00 * i);
			emp.setStartDate(new Date());
			emp.setTitle("사원");
			emp.setCommissionPct(15.00);
			em.persist(emp);
		}
		
		em.getTransaction().commit();
		em.close();
	}
}
