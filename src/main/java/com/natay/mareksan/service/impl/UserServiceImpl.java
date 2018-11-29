package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.User;
import com.natay.mareksan.repository.UserRepository;
import com.natay.mareksan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ramazan on 28.11.2018.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Set<User> getUsers() {
        Set<User> userSet = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(userSet::add);
        return userSet;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
