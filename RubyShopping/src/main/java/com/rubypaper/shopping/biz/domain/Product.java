package com.rubypaper.shopping.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "S_PRODUCT")
@Data
public class Product {
	
	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int quantity;
	
	public void reduceStock(int quantity) {
		this.quantity = this.quantity - quantity;
		
		if(quantity < 0) {
			throw new IllegalStateException("재고가 부족합니다");
		}
	}
	
	public void restoreStock(int quantity) {
		this.quantity = this.quantity + quantity;
	}
}
