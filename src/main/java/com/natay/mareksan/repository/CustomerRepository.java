package com.natay.mareksan.repository;

import com.natay.mareksan.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByAuthorizedEMail(String authorizedEMail);
    Customer findByCompanyName(String companyName);
    Optional<Customer> findById(Long id);
}
