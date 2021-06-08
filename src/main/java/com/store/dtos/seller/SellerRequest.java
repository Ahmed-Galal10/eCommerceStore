package com.store.dtos.seller;



public class SellerRequest {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String image;
    private String phone;
    private Boolean isEmailVerified;
    private Boolean isDeleted;
    private double balance;

    public SellerRequest() {
    }

    public SellerRequest(Integer id, String name, String email, String password, String address,
                         String image, String phone, Boolean isEmailVerified,
                         Boolean isDeleted, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.image = image;
        this.phone = phone;

        this.isEmailVerified = isEmailVerified;
        this.isDeleted = isDeleted;
        this.balance = balance;
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {

        this.isEmailVerified = isEmailVerified;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public SellerRequest(String name, String email, String password, String address,
                         String image, String phone, double balance, Boolean isDeleted, Boolean isEmailVerified) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.image = image;
        this.phone = phone;
        this.balance = balance;
        this.isDeleted = isDeleted;
        this.isEmailVerified = isEmailVerified;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
