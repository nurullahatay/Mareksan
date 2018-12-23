package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Role;
import com.natay.mareksan.repository.CustomerRepository;
import com.natay.mareksan.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void saveCustomer() {

        //Given
        Customer customer = new Customer();
        customer.setActive(1);
        customer.setPassword(passwordEncoder.encode("123"));
        String customerRole = "CUSTOMER";
        Role role = new Role(1, customerRole);

        //When
        when(roleRepository.findByRole(customerRole)).thenReturn(role);
        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.saveCustomer(customer);

        //Then
        verify(customerRepository, Mockito.times(1)).save(customer);
        verify(roleRepository, Mockito.times(1)).findByRole(customerRole);
    }

    @Test
    public void saveAdmin() {

        //Given
        Customer customer = new Customer();
        customer.setActive(1);
        customer.setPassword(passwordEncoder.encode("123"));
        String customerRole = "ADMIN";
        Role role = new Role(1, customerRole);

        //When
        when(roleRepository.findByRole(customerRole)).thenReturn(role);
        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.saveAdmin(customer);

        //Then
        verify(customerRepository, Mockito.times(1)).save(customer);
        verify(roleRepository, Mockito.times(1)).findByRole(customerRole);
    }

    @Test
    public void getCustomers() {
        //Given
        Customer customer = new Customer();
        customer.setActive(1);
        customer.setPassword(passwordEncoder.encode("123"));


        //When
        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));

        Set<Customer> customers = customerService.getCustomers();

        //Then
        verify(customerRepository, times(1)).findAll();

        assertThat(customers).isNotEmpty();
        assertThat(customers).hasSize(1);
        assertThat(customers).containsOnly(customer);
    }
}