package com.natay.mareksan.controller;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.schedule.ScheduledTasks;
import com.natay.mareksan.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class LoginCustomerController {

    private static final Logger log = LoggerFactory.getLogger(LoginCustomerController.class);


    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    public String login(Model model) {
        log.info("--------------->request mareksan");
        model.addAttribute("datetime", new Date());
        model.addAttribute("customer", new Customer());
        model.addAttribute("errorMessage", false);
        return "/customer/customerLogin";
    }

    @PostMapping("/customer/customerLogin")
    public String loginCustomer(@ModelAttribute Customer customer, Model model) {

        customer = customerService.findCustomerByAuthorizedEMailAndPassword(customer.getAuthorizedEMail(), customer.getPassword());

        if (customer == null) {
            model.addAttribute("datetime", new Date());
            model.addAttribute("customer", new Customer());
            model.addAttribute("errorMessage", true);
            return "/customer/customerLogin";
        }

        model.addAttribute("customer", customer);
        return "/customer/customerHome";
    }
}
