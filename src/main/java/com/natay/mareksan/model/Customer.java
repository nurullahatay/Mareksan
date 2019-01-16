package com.natay.mareksan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="customer_id")
    private Long id;

    @NotEmpty(message = "Sirket ismi bos birakilamaz.")
    private String companyName;

    @Pattern(regexp ="[0-9\\s]{10}", message = "Sirket telefonu bos birakilamaz.")
    private String companyPhone;

    //@NotEmpty(message = "Sirket adresi bos birakilamaz.")
    private String companyAddress;

    @NotEmpty(message = "Yetkili ismi bos birakilamaz.")
    private String authorizedName;

    /*@Pattern(regexp ="[0-9\\s]{10}" , message = "Yetkili telefonu bos birakilamaz.")
    private String authorizedPhone;
    */
    @NotEmpty(message = "Yetkili emaili bos birakilamaz.")
    @Email(message = "Hatali email")
    @Column(name = "authorizedEMail", unique = true)
    private String authorizedEMail;

    @NotEmpty(message = "Şifre boş bırakılamaz.")
    private String password;

    @Column(name = "active")
    private int active;


    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_role", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    private String taxOffice;
    private String taxNumber;
    private String title;

    public Customer() {
    }

    public Customer(String companyName, String companyPhone, String companyAddress, String authorizedName, String authorizedEMail, String password, int active, String taxOffice, String taxNumber, String title) {
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
        this.authorizedName = authorizedName;
        //this.authorizedPhone = authorizedPhone;
        this.authorizedEMail = authorizedEMail;
        this.password = password;
        this.active = active;
        this.taxOffice = taxOffice;
        this.taxNumber = taxNumber;
        this.title = title;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

   /* public String getAuthorizedPhone() {
        return authorizedPhone;
    }

    public void setAuthorizedPhone(String authorizedPhone) {
        this.authorizedPhone = authorizedPhone;
    }
*/
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

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
