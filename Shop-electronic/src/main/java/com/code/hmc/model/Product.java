package com.code.hmc.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Component
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private Double price;
    private String urlPicture;
    private int vote;
    @CreationTimestamp
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpload;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_update;
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;
    @ManyToMany
    @JoinTable(name = "products_orderdetail",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "orderdetail_id"))
    private Collection<OrderDetailDB> orderDetailDB;



    public Product() {
    }

    public Product(String name, String description, Double price,
                   String urlPicture, int vote, Date dateUpload, Date date_update, Category category, Producer producer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlPicture = urlPicture;
        this.vote = vote;
        this.dateUpload = dateUpload;
        this.date_update = date_update;
        this.category = category;
        this.producer = producer;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
    public Collection<OrderDetailDB> getOrderDetailDB() {
        return orderDetailDB;
    }

    public void setOrderDetailDB(Collection<OrderDetailDB> orderDetailDB) {
        this.orderDetailDB = orderDetailDB;
    }
}
