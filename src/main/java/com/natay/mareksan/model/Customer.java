package com.natay.mareksan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;
    private String companyPhone;
    private String companyAddress;
    private String authorizedName;
    private String authorizedPhone;
    private String authorizedEMail;
    private String password;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    public Customer() {}

    public Customer(String companyName, String companyPhone, String companyAddress, String authorizedName, String authorizedPhone, String authorizedEMail, String password) {
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
        this.authorizedName = authorizedName;
        this.authorizedPhone = authorizedPhone;
        this.authorizedEMail = authorizedEMail;
        this.password = password;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getAuthorizedName() {
        return authorizedName;
    }

    public void setAuthorizedName(String authorizedName) {
        this.authorizedName = authorizedName;
    }

    public String getAuthorizedPhone() {
        return authorizedPhone;
    }

    public void setAuthorizedPhone(String authorizedPhone) {
        this.authorizedPhone = authorizedPhone;
    }

    public String getAuthorizedEMail() {
        return authorizedEMail;
    }

    public void setAuthorizedEMail(String authorizedEMail) {
        this.authorizedEMail = authorizedEMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
