package com.code.hmc.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String sex;
    private String address;
    private String phone;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Customer() {
    }

    public Customer(String name, String email, String sex, String address, String phone) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public Customer(String name, String email, String sex, String address, String phone, Set<Order> orders) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

