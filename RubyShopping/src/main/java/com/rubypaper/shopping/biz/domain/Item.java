package com.rubypaper.shopping.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "S_ITEM")
@Data
@NoArgsConstructor
@ToString(exclude = {"order", "product"})
public class Item {
	
	@Id
	@GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	private int count;
	
	public Item(Product product, int count) {
		this.product = product;
		this.count = count;
		
		reduceStock(count);
	}
	
	public void reduceStock(int count) {
		product.reduceStock(count);
	}
	
	public void restoreStock() {
		product.restoreStock(count);
	}
}
