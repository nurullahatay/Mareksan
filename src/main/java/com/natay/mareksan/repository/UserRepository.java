package com.natay.mareksan.repository;

import com.natay.mareksan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ramazan on 28.11.2018.
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
