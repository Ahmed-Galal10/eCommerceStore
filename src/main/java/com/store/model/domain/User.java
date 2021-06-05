package com.store.model.domain;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
        ,catalog="ecommerce"
        , uniqueConstraints = {@UniqueConstraint(columnNames="email"), @UniqueConstraint(columnNames="name")}
)
public class User  implements java.io.Serializable {


    private Integer id;
    private String name;
    private String email;
    private String password;
    private String address;
    private Date regDate;
    private double balance;
    private String image;
    private String phone;
    private String role;
    private Boolean isEmailVerified;
    private Boolean isDeleted;
    private Set<Product> products = new HashSet<Product>(0);
    private Set<SoldItem> soldItems = new HashSet<SoldItem>(0);
    private Set<Order> orders = new HashSet<Order>(0);
    private Set<Review> reviews = new HashSet<Review>(0);
    private Set<CartItem> cartItems = new HashSet<CartItem>(0);

    public User() {


    }


    public User(String name, String email, String password, String address, Date regDate, double balance, String image, String phone, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.regDate = regDate;
        this.balance = balance;
        this.image = image;
        this.phone = phone;
        this.role = role;
    }
    public User(String name, String email, String password, String address, Date regDate, double balance, String image, String phone, String role, Boolean isEmailVerified, Boolean isDeleted, Set<Product> products, Set<SoldItem> soldItems, Set<Order> orders, Set<Review> reviews, Set<CartItem> cartItems) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.regDate = regDate;
        this.balance = balance;
        this.image = image;
        this.phone = phone;
        this.role = role;
        this.isEmailVerified = isEmailVerified;
        this.isDeleted = isDeleted;
        this.products = products;
        this.soldItems = soldItems;
        this.orders = orders;
        this.reviews = reviews;
        this.cartItems = cartItems;
    }

    @Id @GeneratedValue(strategy=IDENTITY)


    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Column(name="name", unique=true, nullable=false, length=25)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name="email", unique=true, nullable=false, length=100)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name="password", nullable=false, length=270)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name="address", nullable=false, length=100)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reg_date", nullable=false, length=19)
    public Date getRegDate() {
        return this.regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }


    @Column(name="balance", nullable=false, precision=22, scale=0)
    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    @Column(name="image", nullable=false, length=250)
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Column(name="phone", nullable=false, length=45)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Column(name="role", nullable=false, length=45)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Column(name="isEmailVerified")
    public Boolean getIsEmailVerified() {
        return this.isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }


    @Column(name="isDeleted")
    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<SoldItem> getSoldItems() {
        return this.soldItems;
    }

    public void setSoldItems(Set<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", regDate=" + regDate +
                ", balance=" + balance +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", isEmailVerified=" + isEmailVerified +
                ", isDeleted=" + isDeleted +
                '}';
    }
}


