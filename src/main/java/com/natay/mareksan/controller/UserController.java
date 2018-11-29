package com.natay.mareksan.controller;

import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.User;
import com.natay.mareksan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
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

    @GetMapping("/getUser/{userId}")
    public Optional<User> getUserById(@PathVariable String userId){
        return userService.getUserById(Long.valueOf(userId));
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Object> deleteById(@PathVariable  Long userId){
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody User user)
    {
        Optional<User> currentUser = userService.getUserById(user.getId());

        if (currentUser==null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

        currentUser.get().setName(user.getName());
        currentUser.get().setSurname(user.getSurname());
        currentUser.get().setEmail(user.getEmail());
        currentUser.get().setPhone(user.getPhone());
        currentUser.get().setPassword(user.getPassword());

        userService.updateUser(currentUser.get());
        return new ResponseEntity<Object>(currentUser, HttpStatus.OK);
    }



}
