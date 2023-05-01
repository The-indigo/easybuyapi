package com.example.easybuyapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{
	
	ProductModel findByName(String name);
	ProductModel findById(int id);
	List<ProductModel> findByCategory(String category);
}
