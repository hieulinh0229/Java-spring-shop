package com.code.hmc.model;
import java.security.acl.LastOwnerException;
import java.util.List;

public class OrderDetail {

    private long id;
    private List<IteamInCart> iteam;
    private double amount;
    private long product_id;
    private double price;
    private double totalMoney;

    private Order order;

    public OrderDetail() {
    }

    public OrderDetail(long id, List<IteamInCart> iteam, double amount, int product_id, double price, double totalMoney, Order order) {
        this.id = id;
        this.iteam = iteam;
        this.amount = amount;
        this.product_id = product_id;
        this.price = price;
        this.totalMoney = totalMoney;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<IteamInCart> getIteam() {
        return iteam;
    }

    public void setIteam(List<IteamInCart> iteam) {
        this.iteam = iteam;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
