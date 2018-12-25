package com.natay.mareksan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "`ORDERS`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String orderName;

    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$" ,message = "Tarih formati hatali.")
    private String orderDate;
    private String deliveryDate;

    @Min(value = 1)
    private int amount;
    @NotEmpty
    private String specificationsOrders;

    @DecimalMin(value = "0.1", inclusive = true)
    private double price;

    @DecimalMin(value = "0.0", inclusive = true)
    private double paid;

    @DecimalMin(value = "0.0", inclusive = true)
    private double remainder;

    @NotEmpty
    private String description;
    private boolean visibility;

    //@Enumerated(EnumType.STRING)
    @NotEmpty
    private String orderStatus;

    //@Enumerated(EnumType.STRING)
    @NotEmpty
    private String orderType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("orders")
    private Customer customer;

    public Order() {
    }

    public Order(String orderName, String orderDate, String deliveryDate, int amount, String specificationsOrders, double price, double paid, double remainder, String description, String orderStatus, String orderType, Customer customer , boolean visibility) {
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amount = amount;
        this.specificationsOrders = specificationsOrders;
        this.price = price;
        this.paid = paid;
        this.remainder = remainder;
        this.description = description;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.customer = customer;
        this.visibility = visibility;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
