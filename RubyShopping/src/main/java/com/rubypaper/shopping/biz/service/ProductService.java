package com.rubypaper.shopping.biz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.shopping.biz.domain.Product;
import com.rubypaper.shopping.biz.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void insertOrUpdateProduct(Product product) {
		productRepository.insertOrUpdateProduct(product);
	}
	
	public Product getProduct(Long id) {
		return productRepository.getProduct(id);
	}
	
	public List<Product> getProductList(){
		return productRepository.getProductList();
	}
	
}
