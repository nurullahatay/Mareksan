package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Role;
import com.natay.mareksan.repository.CustomerRepository;
import com.natay.mareksan.repository.RoleRepository;
import com.natay.mareksan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {
        createCustomerWithRole(customer, "CUSTOMER");
    }

    @Override
    public void saveAdmin(Customer customer) {
        createCustomerWithRole(customer, "ADMIN");
    }

    private void createCustomerWithRole(Customer customer, String role) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customer.setActive(1);
        Role customerRole = roleRepository.findByRole(role);
        customer.setRoles(Collections.singleton(customerRole));
        customerRepository.save(customer);
    }

    @Override
    public Set<Customer> getCustomers() {
        return getCustomerWithRole("ADMIN");
    }

    @Override
    public Set<Customer> getAdmins() {
        return getCustomerWithRole("CUSTOMER");
    }


    private Set<Customer> getCustomerWithRole(String role) {
        Set<Customer> customerSet = new HashSet<>();
        customerRepository
                .findAll()
                .iterator()
                .forEachRemaining(customerSet::add);
        Role customerRole = roleRepository.findByRole(role);
        customerSet.removeIf(customer -> customer.getRoles().contains(customerRole));
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
    public Customer findCustomerByAuthorizedEMail(String authorizedEMail) {
        return customerRepository.findCustomerByAuthorizedEMail(authorizedEMail);
    }

    @Override
    public void deleteById(Long customerId) {

        customerRepository.deleteById(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }
}
