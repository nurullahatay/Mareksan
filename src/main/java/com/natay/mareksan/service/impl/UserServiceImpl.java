package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.User;
import com.natay.mareksan.repository.UserRepository;
import com.natay.mareksan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
