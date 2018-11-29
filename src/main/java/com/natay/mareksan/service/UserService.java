package com.natay.mareksan.service;

import com.natay.mareksan.model.User;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Ramazan on 28.11.2018.
 */
public interface UserService {
    void saveUser(User user);
    Set<User> getUsers();
    Optional<User> getUserById(Long userId);
    void deleteById(Long userId);
    User getUserByEmailAndPassword(String email , String password);
    void updateUser(User user);
}
