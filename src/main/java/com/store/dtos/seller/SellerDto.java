package com.store.dtos.seller;

import java.util.Date;

public class SellerDto {

    private Integer id;
    private String name;
    private String email;
    private String address;
    private Date regDate;
    private double balance;
    private String image;
    private String phone;
    private Boolean isEmailVerified;
    private Boolean isDeleted;

    public SellerDto() {
    }

    public SellerDto(Integer id, String name, String email,
                     String address, Date regDate, double balance, String image,
                     String phone, Boolean isEmailVerified, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.regDate = regDate;
        this.balance = balance;
        this.image = image;
        this.phone = phone;

        this.isEmailVerified = isEmailVerified;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.isEmailVerified = emailVerified;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }
}
