package com.natay.mareksan.controller;


import com.natay.mareksan.model.Customer;
import com.natay.mareksan.service.CustomerService;
import com.natay.mareksan.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/saveCustomer")
    public ResponseEntity<Object> saveCustomer(@Valid @RequestBody Customer customer) {
        log.info("--------------->"+customer.getCompanyName());
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Set<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/companyName/{companyName}")
    public Customer findByCompanyName(@PathVariable String companyName) {
        return customerService.findByCompanyName(companyName);
    }

    @GetMapping("/getCustomer/{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerById(Long.valueOf(customerId));
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long customerId) {
        orderService.deleteOrdersByCustomerId(customerId);
        customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer){
        Optional<Customer> currentCustomer = customerService.getCustomerById(customer.getId());
        if (currentCustomer == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        currentCustomer.get().setCompanyName(customer.getCompanyName());
        currentCustomer.get().setCompanyPhone(customer.getCompanyPhone());
        currentCustomer.get().setCompanyAddress(customer.getCompanyAddress());
        currentCustomer.get().setAuthorizedName(customer.getAuthorizedName());
        currentCustomer.get().setAuthorizedEMail(customer.getAuthorizedEMail());
        currentCustomer.get().setAuthorizedPhone(customer.getAuthorizedPhone());
        currentCustomer.get().setPassword(customer.getPassword());

        customerService.updateCustomer(currentCustomer.get());
        return new ResponseEntity<Object>(currentCustomer, HttpStatus.OK);
    }

}
