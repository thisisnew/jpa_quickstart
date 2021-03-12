package com.rubypaper.biz.client;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Item;
import com.rubypaper.biz.domain.Order;
import com.rubypaper.biz.domain.Order2;
import com.rubypaper.biz.domain.Product;
import com.rubypaper.biz.domain.Product2;

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
		
		Order2 order = em.find(Order2.class, 1L);
		System.out.println("주문 날짜 : " + order.getOrderDate());
		
		System.out.println("[ 주문 목록 ]");
		List<Item> itemList = order.getItemList();
		for(Item i : itemList) {
			System.out.println("--->" + i.getProduct().getName());
		}
		
	}
	
	static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Product2 product1 = new Product2();
		product1.setName("LG 통돌이 세탁기");
		em.persist(product1);
		
		Product2 product2 = new Product2();
		product2.setName("다이슨 청소기");
		em.persist(product2);
		
		Order2 order = new Order2();
		order.setOrderDate(new Date());
		em.persist(order);
		
		Item item1 = new Item();
		item1.setOrder(order);
		item1.setProduct(product1);
		item1.setPrice(100000L);
		item1.setQuantity(2L);
		em.persist(item1);
		
		Item item2 = new Item();
		item2.setOrder(order);
		item2.setProduct(product2);
		item2.setPrice(320000L);
		item2.setQuantity(3L);
		em.persist(item2);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
