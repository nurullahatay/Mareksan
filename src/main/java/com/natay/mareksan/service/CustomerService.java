package com.natay.mareksan.service;

import com.natay.mareksan.model.Customer;

import java.util.List;
import java.util.Set;

/**
 * Created by Ramazan on 28.11.2018.
 */
public interface CustomerService {
    void saveCustomer(Customer customer);
    Set<Customer> getCustomers();
    Customer findByCompanyName(String companyName);
    Customer getCustomerById(Long customerId);
    void deleteById(Long customerId);
    void updateCustomer(Customer customer);
}
