package com.natay.mareksan.repository;

import com.natay.mareksan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailAndPassword(String email,String password);
}
