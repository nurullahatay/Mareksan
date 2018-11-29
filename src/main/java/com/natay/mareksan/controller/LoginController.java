package com.natay.mareksan.controller;

import com.natay.mareksan.model.User;
import com.natay.mareksan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("user", new User());
        model.addAttribute("errorMessage",false);

        return "userLogin";
    }

    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute User user,Model model) {

          user =  userService.getUserByEmailAndPassword(user.getEmail(),user.getPassword());

          if (user == null)
          {
              model.addAttribute("datetime", new Date());
              model.addAttribute("user", new User());
              model.addAttribute("errorMessage",true);
              return "userLogin";
          }

        model.addAttribute("user",user);
        return "userHome";
    }
}
