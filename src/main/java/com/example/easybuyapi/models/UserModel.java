package com.example.easybuyapi.models;

public class UserModel {
    private int userID;
    

    private String email;
    private String password;
    private String confirmPassword;
    private String fullname;
    private Integer phoneNumber;
    private String address;

    //Constructor
    public UserModel(String email, String password, String confirmPassword, String fullname, Integer phoneNumber,
            String address) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    //Getter and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    

    
}
