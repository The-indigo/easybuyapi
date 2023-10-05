package com.example.easybuyapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.easybuyapi.models.Product;
import com.example.easybuyapi.services.ProductService;


@RestController
@RequestMapping("/easybuyapi/v1")
public class ProductController implements ErrorController {

    /**
     * Indicates that the ProductService should be autowired
     * by spring's dependency injection mechanism.
     **/
    @Autowired
    private ProductService productService;

    /**
     * This method adds a product item to the databse.
     * @return Product
     * @param newProduct
     **/
    @PostMapping("/product/add")
    @ResponseStatus(value = HttpStatus.OK)
    public Product addProduct(@RequestBody Product newProduct
    ) throws Exception {
        return productService.addProductService(
            newProduct.getName(),
            newProduct.getPrice(),
        newProduct.getImage(), newProduct.getCategory());
    }


  /**
     * Gets the list of all products in the databse.
     * @return Iterable<Product>
     **/
    @GetMapping("/product/all")
   public Iterable<Product> getAllProducts() {
        return productService.getAllProductService();
    }


 /**
     * Updates a product item in the databse.
     * @return Product
     * @param id
     * @param product
     **/
    @PostMapping("/product/update/{id}")
    public Product updateProduct(@PathVariable("id") int id,
     @RequestBody Product product) throws Exception{
        return productService.updateProductService(
            id, product.getName(), product.getPrice(), product.getImage(),
            product.getCategory());
        }

 /**
     * Updates a product item in the databse.
     * @return String
     * @param id
     **/
	@DeleteMapping("/product/delete/{id}")
		public String deleteProduct(@PathVariable("id") int id) throws Exception{
			productService.deleteProductService(id);
			return ("Deleted successfully");
		}
}