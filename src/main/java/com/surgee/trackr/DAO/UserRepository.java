package com.surgee.trackr.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surgee.trackr.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByEmail(String email);
}
