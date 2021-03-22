package com.rubypaper.shopping.biz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.shopping.biz.domain.Customer;
import com.rubypaper.shopping.biz.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public void insertCustomer(Customer customer) {
		customerRepository.insertCustomer(customer);
	}
	
	public Customer getCustomer(Long id) {
		return customerRepository.getCustomer(id);
	}
	
	public List<Customer> getCustomerList() {
		return customerRepository.getCustomerList();
	}
}
