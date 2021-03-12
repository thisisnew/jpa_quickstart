package com.rubypaper.biz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_ITEM")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ORD_ID")
	private Order2 order;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product2 product;
	
	private Long price;
	
	private Long quantity;
	
	public void setOrder(Order2 order) {
		this.order = order;
		order.getItemList().add(this);
	}
	
}
