package com.natay.mareksan.repository;

import com.natay.mareksan.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Ramazan on 28.11.2018.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCompanyName(String companyName);
    Optional<Customer> findById(Long id);
}
