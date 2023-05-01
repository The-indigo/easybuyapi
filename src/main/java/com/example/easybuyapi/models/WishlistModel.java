package com.example.easybuyapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="wishlist")
public class WishlistModel {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="userid")
    private int userId;
    @Column(name="productid")
    private int productId;


    public WishlistModel() {
    }

    public WishlistModel(int id, int userId, int productId) {
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

    public WishlistModel id(int id) {
        setId(id);
        return this;
    }

    public WishlistModel userId(int userId) {
        setUserId(userId);
        return this;
    }

    public WishlistModel productId(int productId) {
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
}

