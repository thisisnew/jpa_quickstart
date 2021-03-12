package com.rubypaper.biz.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "itemList")
@Entity
@Table(name = "S_ORD")
public class Order2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	private Double total;
	
	@OneToMany(mappedBy = "order")
	private List<Item> itemList = new ArrayList<>();
	
}
