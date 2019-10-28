package com.code.hmc.model;

public class IteamInCart {
    private int id;
    private Product product;
    private int quantity;
    private double price;

    public IteamInCart() {
    }

    public IteamInCart(int id, Product product, int quantity, double price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice(Double price) {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
