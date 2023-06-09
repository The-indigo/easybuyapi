package com.example.easybuyapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="wishlist")
public class Wishlist {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="userid")
    private int userId;
    @Column(name="productid")
    private int productId;


    public Wishlist() {
    }

      public Wishlist(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Wishlist(int id, int userId, int productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Wishlist id(int id) {
        setId(id);
        return this;
    }

    public Wishlist userId(int userId) {
        setUserId(userId);
        return this;
    }

    public Wishlist productId(int productId) {
        setProductId(productId);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", productId='" + getProductId() + "'" +
            "}";
    }

	public Wishlist orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}


}

