package com.rubypaper.biz.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class CriteriaSearchClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter07");
		
		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}
	
	static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Department devDept = new Department();
		devDept.setName("개발부");
		em.persist(devDept);
		
		Department salesDept = new Department();
		salesDept.setName("영업부");
		em.persist(salesDept);
		
		
		for(int i=1; i<=3; i++) {
			Employee emp = new Employee();
			emp.setName("개발맨"+i);
			emp.setMailId("Corona" + i);
			emp.setDept(devDept);
			emp.setSalary(12700.00 * i);
			emp.setStartDate(new Date());
			emp.setTitle("사원");
			emp.setCommissionPct(10.00);
			em.persist(emp);
		}
		
		for(int i=1; i<=3; i++) {
			Employee emp = new Employee();
			emp.setName("영업맨"+i);
			emp.setMailId("Virus" + i);
			emp.setDept(salesDept);
			emp.setSalary(23800.00 * i);
			emp.setStartDate(new Date());
			emp.setTitle("과장");
			emp.setCommissionPct(15.00);
			em.persist(emp);
		}
		
		Employee emp = new Employee();
		emp.setName("아르바이트");
		emp.setMailId("Alba-01");
		emp.setSalary(10000.00);
		em.persist(emp);
		
		em.getTransaction().commit();
		em.close();
	}
	
	static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		Root<Department> dept = criteriaQuery.from(Department.class);
		
		Join<Department, Employee> emp = dept.join("employeeList");
		
		criteriaQuery.multiselect(dept.get("name"),
							emp.get("name"));
		
		
		TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
		
		List<Object[]> resultList = query.getResultList();
		
		System.out.println("결과 조회");
		
		for(Object[] result : resultList) {
			System.out.println("->>>" + Arrays.toString(result));
		}
	}
}
