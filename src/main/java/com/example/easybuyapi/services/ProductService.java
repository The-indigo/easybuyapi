package com.example.easybuyapi.services;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easybuyapi.models.Product;
import com.example.easybuyapi.repositories.ProductRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    /*@PostConstruct
    public void initializeDatabase() {
        // Create a list of product data
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Apple iPhone 12", 999.99, "iphone12.jpg", "Electronics"));
        productList.add(new Product("Samsung Galaxy S21", 899.99, "galaxyS21.jpg", "Electronics"));
        productList.add(new Product("Sony PlayStation 5", 499.99, "ps5.jpg", "Gaming"));
        productList.add(new Product("LG 4K Ultra HD Smart TV", 1299.99, "lgtv.jpg", "Electronics"));
        productList.add(new Product("T-Shirt", 19.99, "tshirt.jpg", "Clothing"));
        productList.add(new Product("Smartphone", 499.99, "smartphone.jpg", "Electronics"));
        productList.add(new Product("Running Shoes", 79.99, "runningshoes.jpg", "Clothing"));
        productList.add(new Product("Bluetooth Speaker", 59.99, "bluetoothspeaker.jpg", "Electronics"));
        productList.add(new Product("iWatch", 129.99, "watch.jpg", "Electronics"));
        productList.add(new Product("Dell Inspiron 302", 999.99, "laptop.jpg", "Electronics"));
        productList.add(new Product("Gucci 22", 149.99, "handbag.jpg", "Clothing"));
        productList.add(new Product("Beats By Ye", 89.99, "headphones.jpg", "Electronics"));
        productList.add(new Product("Prade Darkshades", 39.99, "sunglasses.jpg", "Clothing"));
        productList.add(new Product("Fitness Wristwear", 69.99, "fitnesstracker.jpg", "Electronics"));
        
        // Save the products to the database
        productRepository.saveAll(productList);
    }
    */
   
    public Product addProductService(String name, Double price, String image, String category) throws Exception {
        if (name.isBlank() || image.isBlank() || category.isBlank() || Double.isNaN(price)) {
            throw new Exception("There are missing files");
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
            throw new Exception("There are missing files");
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
        boolean product= productRepository.existsById(id);
        if(!product){
            throw new Exception("Product does not exist");
        }   
        productRepository.deleteById(id);
    }

}

