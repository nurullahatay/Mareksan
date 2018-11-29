package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.repository.CustomerRepository;
import com.natay.mareksan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Ramazan on 28.11.2018.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Set<Customer> getCustomers() {
        Set<Customer> customerSet = new HashSet<>();
        customerRepository.findAll().iterator().forEachRemaining(customerSet::add);
        return customerSet;
    }

    @Override
    public Customer findByCompanyName(String companyName) {
        return customerRepository.findByCompanyName(companyName);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {

        return customerRepository.findById(customerId);
    }

    @Override
    public void deleteById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
