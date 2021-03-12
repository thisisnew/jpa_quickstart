package com.rubypaper.biz.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.EmployeeCard;

public class OneToOneBothWayClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("Chapter05");
		
		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
	
		EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
		System.out.println("검색된 사원증 번호 : " + employeeCard.getCardId());
		System.out.println("권한 : " + employeeCard.getRole());
		System.out.println("사원증 소유자 : " + employeeCard.getEmployee().getName());
		
		Employee employee = em.find(Employee.class, 1L);
		System.out.println("검색된 직원 이름 : " + employee.getName());
		System.out.println("직원이 소유한 사원증 권한  : " + employee.getCard().getRole());
	}
	
	static void dataInsert(EntityManagerFactory emf) throws ParseException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Employee employee = new Employee();
		employee.setName("둘리");
		em.persist(employee);

		EmployeeCard card = new EmployeeCard();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		card.setExpireDate(sdf.parse("2025-12-31"));
		card.setRole("MASTER");
		card.setEmployee(employee);
		em.persist(card);
		
		em.getTransaction().commit();
		em.close();
	}
}
