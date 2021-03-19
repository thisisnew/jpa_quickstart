package com.rubypaper.shopping.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "S_ORDER")
@Data
@NoArgsConstructor
@ToString(exclude = {"customer","searchCustomerName","searchOrderStatus"})
public class Order {
	
	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private Date orderDate;
	
	@Transient
	private String searchCustomerName;
	
	@Transient
	private OrderStatus searchOrderStatus;
	
	public Order(Customer customer) {
		setCustomer(customer);
		this.status = OrderStatus.ORDER;
		this.orderDate = new Date();
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		customer.getOrderList().add(this);
	}
}
