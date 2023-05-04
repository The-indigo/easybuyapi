package com.example.easybuyapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.easybuyapi.models.Product;
import com.example.easybuyapi.repositories.ProductRepository;


@RestController
public class ProductController implements ErrorController {
	@Autowired
	private ProductRepository productRepo;

	//create new product
    @RequestMapping(value = "/easybuyapi/addProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void addProduct(@RequestBody Product newProduct) throws Exception {
    	addProductService(newProduct);
    }
    
    //return and displays the list of all product
    @RequestMapping(value = "/easybuyapi/productList", method = RequestMethod.GET)
    Iterable<Product> getAllProducts() {
        return getAllProductService();
    }
    
    //service method to get list of all product 
  	public Iterable<Product> getAllProductService(){
  		return productRepo.findAll();
  	}

	//service method to add a new product to list
	public void addProductService(Product newProduct) throws Exception{
		//check if product already exist. if yes, you cannot create
		if(productRepo.findById(newProduct.getId()) != null) {
			throw new Exception("This product Id already exist: "+ newProduct.getId());
		}
		else {
			productRepo.save(newProduct);
			System.out.println("Successfully added");
			
		}
	}

    @GetMapping("/easybuyapi/v1/products/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category)  {
        List<Product> products = productRepo.findByCategory(category);
        if(products.isEmpty()) {
			System.out.println("Not Successfully added");
            return ResponseEntity.noContent().build();
        }
		System.out.println("Successfully added");
        return ResponseEntity.ok(products);
    }
    


}