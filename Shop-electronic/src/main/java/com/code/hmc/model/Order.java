package com.code.hmc.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;
    @CreationTimestamp
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_order;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_update;
    private double totalMoney;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderDetailDB> orderDetailDBS;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    public Order() {
    }

    public Order(Date date_order, Date date_update, double totalMoney, Customer customer, Set<OrderDetailDB> orderDetailDBS, Status status) {
        this.date_order = date_order;
        this.date_update = date_update;
        this.totalMoney = totalMoney;
        this.customer = customer;
        this.orderDetailDBS = orderDetailDBS;
        this.status = status;
    }


    public Order(Date date_order, Date date_update, double totalMoney, Customer customer, Set<OrderDetailDB> orderDetailDBS) {
        this.date_order = date_order;
        this.date_update = date_update;

        this.totalMoney = totalMoney;
        this.customer = customer;
        this.orderDetailDBS = orderDetailDBS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate_order() {
        return date_order;
    }

    public void setDate_order(Date date_order) {
        this.date_order = date_order;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }


    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderDetailDB> getOrderDetailDBS() {
        return orderDetailDBS;
    }

    public void setOrderDetailDBS(Set<OrderDetailDB> orderDetailDBS) {
        this.orderDetailDBS = orderDetailDBS;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
