package com.code.hmc.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orderdetail")
public class OrderDetailDB extends OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double amount;
    private long product_id;
    private double price;
    private double totalMoney;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToMany(mappedBy = "orderDetailDB")
    private Collection<Product> product;
    public OrderDetailDB() {
    }

    public OrderDetailDB(double amount, long product_id, double price, double totalMoney, Order order, Collection<Product> product) {
        this.amount = amount;
        this.product_id = product_id;
        this.price = price;
        this.totalMoney = totalMoney;
        this.order = order;
        this.product = product;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getTotalMoney() {
        return totalMoney;
    }

    @Override
    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
    }

}
