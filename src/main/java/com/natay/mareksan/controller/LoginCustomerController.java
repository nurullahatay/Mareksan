package com.natay.mareksan.controller;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.User;
import com.natay.mareksan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class LoginCustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer")
    public String login(Model model) {
        model.addAttribute("datetime", new Date());
        model.addAttribute("customer", new Customer());
        model.addAttribute("errorMessage", false);

        return "customerLogin";
    }

    @PostMapping("/loginCustomer")
    public String loginCustomer(@ModelAttribute Customer customer, Model model) {

        customer = customerService.findCustomerByAuthorizedEMailAndPassword(customer.getAuthorizedEMail(), customer.getPassword());

        if (customer == null) {
            model.addAttribute("datetime", new Date());
            model.addAttribute("customer", new Customer());
            model.addAttribute("errorMessage", true);
            return "customerLogin";
        }

        model.addAttribute("customer", customer);
        return "customerHome";
    }
}
