package com.natay.mareksan.controller;

import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.User;
import com.natay.mareksan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user)
    {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Set<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Object> deleteById(@PathVariable  Long userId){
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
