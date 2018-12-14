package com.natay.mareksan.controller;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class AuthenticatedUser {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public Customer currentUserNameSimple(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println( auth.getAuthorities().toString());
        Principal principal = request.getUserPrincipal();
        return customerService.findCustomerByAuthorizedEMail(principal.getName());
    }
}
