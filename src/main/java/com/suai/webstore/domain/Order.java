package com.suai.webstore.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "mail")
    private String mail;

    @Column(name = "list_product")
    private String listProduct;

    public Order(){

    }

    public Order(Long userId , String username, String address, String mail, String listProduct){
        this.userId = userId ;
        this.username = username;
        this.address = address;
        this.mail = mail;
        this.listProduct = listProduct;

    }


    public String getListProduct() {
        return listProduct;
    }

    public void setListProduct(String listProduct) {
        this.listProduct = listProduct;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
