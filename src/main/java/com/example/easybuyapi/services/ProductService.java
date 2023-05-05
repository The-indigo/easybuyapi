package com.example.easybuyapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easybuyapi.models.Product;
import com.example.easybuyapi.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product addProductService(String name, Double price, String image, String category) throws Exception {
        if (name.isBlank() || image.isBlank() || category.isBlank() || Double.isNaN(price)) {
            throw new Exception("There are missing fileds");
        }

        var findProduct = productRepository.findByName(name);
        if (!(findProduct.getName().isBlank())) {
            throw new Exception("Not you trying to add an already existing product");
        }

        Product product = new Product(name, price, image, category);
        product = productRepository.save(product);
        return product;
    }

    // service method to get list of all product
    public Iterable<Product> getAllProductService() {
        return productRepository.findAll();
    }

    public Product updateProductService(int id, String name, Double price,String image,String category) throws Exception{
        if (name.isBlank() || image.isBlank() || category.isBlank() || Double.isNaN(price)) {
            throw new Exception("There are missing fileds");
        }
        var product= productRepository.findById(id);
        if(product.isEmpty()){
            throw new Exception ("Product not found");
        }
        Product productObject=new Product(id,name,price,image,category);
        productObject=productRepository.save(productObject);
        return productObject;
    }

    public void deleteProductService(int id) throws Exception{
        var product= productRepository.findById(id);
        if(product.isEmpty()){
            throw new Exception("Product does not exist");
        }   
        productRepository.deleteById(id);
    }

}

