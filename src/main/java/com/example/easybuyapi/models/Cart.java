package com.example.easybuyapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="userid")
    private int userId;
    @Column(name="productid")
    private int productId;
    @Column(name="quantity")
    private int quantity;


    public Cart() {
    }

       public Cart( int userId,  int productId,  int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart( int id,  int userId,  int productId,  int quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return this.id;
    }

    public  void setId( int id) {
        this.id = id;
    }

    public  int getUserId() {
        return this.userId;
    }

    public void setUserId( int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId( int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public  void setQuantity( int quantity) {
        this.quantity = quantity;
    }

    public  Cart id( int id) {
        setId(id);
        return this;
    }

    public  Cart userId( int userId) {
        setUserId(userId);
        return this;
    }

    public  Cart productId( int productId) {
        setProductId(productId);
        return this;
    }

    public  Cart quantity( int quantity) {
        setQuantity(quantity);
        return this;
    }

    @Override
    public  String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }


}
