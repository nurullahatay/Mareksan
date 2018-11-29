package com.natay.mareksan.controller;


import com.natay.mareksan.model.Customer;
import com.natay.mareksan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/saveCustomer")
    public ResponseEntity<Object> saveCustomer(@Valid @RequestBody Customer customer)
    {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Set<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/companyName/{companyName}")
    public Customer findByCompanyName(@PathVariable String companyName){
        return customerService.findByCompanyName(companyName);
    }

    @GetMapping("/getCustomer/{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable String customerId){
        return customerService.getCustomerById(Long.valueOf(customerId));
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Object> deleteById(@PathVariable  Long customerId){
        customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
