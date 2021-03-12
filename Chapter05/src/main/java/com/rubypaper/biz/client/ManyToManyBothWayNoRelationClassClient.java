package com.rubypaper.biz.client;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Order;
import com.rubypaper.biz.domain.Product;

public class ManyToManyBothWayNoRelationClassClient {

	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("Chapter05");
		
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
		
		Order order = em.find(Order.class, 1L);
		System.out.println(order.getId() + "번 주문에 대한 상품 목록");
		
		List<Product> proList = order.getProductList();
		for(Product p : proList) {
			System.out.println("----->" + p.getName());
		}
		
		Product product = em.find(Product.class, 1L);
		
		System.out.println(product.getName() + " 상품에 대한 주문 정보");
		List<Order> orderList = product.getOrderList();
		for(Order o : orderList) {
			System.out.println("---->" + o.toString());
		}
		
	}
	
	static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Product product1 = new Product();
		product1.setName("LG 통돌이 세탁기");
		em.persist(product1);
		
		Product product2 = new Product();
		product2.setName("다이슨 청소기");
		em.persist(product2);
		
		Order order1 = new Order();
		order1.setOrderDate(new Date());
		order1.addProduct(product1);
		em.persist(order1);
		
		Order order2 = new Order();
		order2.setOrderDate(new Date());
		order2.addProduct(product1);
		em.persist(order2);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
