package com.surgee.trackr.user.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surgee.trackr.user.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByEmail(String email);
}
