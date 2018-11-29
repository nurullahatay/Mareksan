package com.natay.mareksan.service;

import com.natay.mareksan.model.Customer;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Ramazan on 28.11.2018.
 */
public interface CustomerService {
    void saveCustomer(Customer customer);
    Set<Customer> getCustomers();
    Customer findByCompanyName(String companyName);
    Optional<Customer> getCustomerById(Long customerId);
    Customer findCustomerByAuthorizedEMailAndPassword(String authorizedEMail, String password);
    void deleteById(Long customerId);
    void updateCustomer(Customer customer);
}
