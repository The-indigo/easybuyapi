package com.example.easybuyapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Product findByName(String name);
	Product findById(int id);
	List<Product> findByCategory(String category);
}
