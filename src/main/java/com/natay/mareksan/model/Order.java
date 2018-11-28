package com.natay.mareksan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`ORDERS`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderName;
    private Date orderDate;
    private Date deliveryDate;
    private String orderType;
    private int amount;
    private String specificationsOrders;
    private double price;
    private double paid;
    private double remainder;
    private String description;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("orders")
    private Customer customer;

    public Order() {
    }

    public Order(String orderName, Date orderDate, Date deliveryDate, String orderType, int amount, String specificationsOrders, double price, double paid, double remainder, String description, OrderStatus orderStatus, Customer customer) {
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderType = orderType;
        this.amount = amount;
        this.specificationsOrders = specificationsOrders;
        this.price = price;
        this.paid = paid;
        this.remainder = remainder;
        this.description = description;
        this.orderStatus = orderStatus;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSpecificationsOrders() {
        return specificationsOrders;
    }

    public void setSpecificationsOrders(String specificationsOrders) {
        this.specificationsOrders = specificationsOrders;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getRemainder() {
        return remainder;
    }

    public void setRemainder(double remainder) {
        this.remainder = remainder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
