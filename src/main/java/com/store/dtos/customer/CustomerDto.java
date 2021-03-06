package com.store.dtos.customer;


import java.io.Serializable;
import java.util.Date;


public class CustomerDto implements Serializable {

        private Integer id;
        private String name;
        private String email;
        private String password;
        private String address;
        private Date regDate;
        private double balance;
        private String image;
        private String phone;
        private Boolean isEmailVerified;
        private Boolean isDeleted;

        public CustomerDto() {
        }

        public CustomerDto(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public CustomerDto(String name, String email, String password, String address, double balance, String phone) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.address = address;
            this.balance = balance;
            this.phone = phone;
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
            isEmailVerified = emailVerified;
        }

        public Boolean getDeleted() {
            return isDeleted;
        }

        public void setDeleted(Boolean deleted) {
            isDeleted = deleted;
        }

        @Override
        public String toString() {
            return "CustomerDto{" +
                    "customerId=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", address='" + address + '\'' +
                    ", regDate=" + regDate +
                    ", balance=" + balance +
                    ", image='" + image + '\'' +
                    ", phone='" + phone + '\'' +
                    ", isEmailVerified=" + isEmailVerified +
                    ", isDeleted=" + isDeleted +
                    '}';
        }
    }

