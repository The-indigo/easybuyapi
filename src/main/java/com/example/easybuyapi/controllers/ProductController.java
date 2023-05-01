package com.example.easybuyapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.easybuyapi.models.ProductModel;
import com.example.easybuyapi.models.UserModel;
import com.example.easybuyapi.repositories.ProductRepository;
import com.james.Cruise;

@RestController
public class ProductController implements ErrorController {

	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/easybuyapi/product")
    public String productPage() {
        // Your product dashboard logic here
        // You can see the list of all products with all product info etc.
        // Return statement for now to test
        return "This is the product page !";
    }
	
	//create new cruise
    @RequestMapping(value = "/easybuyapi/addProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void addProduct(@RequestBody ProductModel newProduct) throws Exception {
    	cruiseService.addCruise(newCruise);
    }

}