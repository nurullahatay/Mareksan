package com.natay.mareksan.repository;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByAuthorizedEMailAndPassword(String authorizedEMail , String password);
    Customer findByCompanyName(String companyName);
    Optional<Customer> findById(Long id);
}
