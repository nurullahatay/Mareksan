package com.natay.mareksan.repository;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailAndPassword(String email,String password);
    Optional<User> findById(Long id);
}
