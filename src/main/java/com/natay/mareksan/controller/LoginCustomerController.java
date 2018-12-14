package com.natay.mareksan.controller;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;


@Controller
public class LoginCustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value={"/customerLogin","/"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ADMIN")) {
            modelAndView.setViewName("admin/userHome");
        }else  if (roles.contains("CUSTOMER")){
            modelAndView.setViewName("customer/customerHome");
        }else {
            modelAndView.setViewName("customerLogin");
        }
        return modelAndView;
    }



    @RequestMapping(value="/customer/customerHome", method = RequestMethod.GET)
    public ModelAndView homeCustomer(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findCustomerByAuthorizedEMail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + customer.getAuthorizedName() + " " + customer.getAuthorizedPhone() + " (" + customer.getAuthorizedEMail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Customer Role");
        modelAndView.setViewName("customer/customerHome");
        return modelAndView;
    }

    @RequestMapping(value="/admin/userHome", method = RequestMethod.GET)
    public ModelAndView homeAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findCustomerByAuthorizedEMail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + customer.getAuthorizedName() + " " + customer.getAuthorizedPhone() + " (" + customer.getAuthorizedEMail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Customer Role");
        modelAndView.setViewName("admin/userHome");
        return modelAndView;
    }

    @RequestMapping(value={"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView access_denied(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("access_denied");
        return modelAndView;
    }
}
