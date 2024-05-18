package com.surgee.trackr.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.surgee.trackr.user.DAO.UserRepository;
import com.surgee.trackr.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User updateUserProfile(Long userId, User formData) {
        Optional<User> userOptional = repository.findById(userId);
        User user = userOptional.get();

        if(user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("First name and last name are required");
        }

        User userBuild = User.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .address(formData.getAddress())
        .phoneNumber(formData.getPhoneNumber())
        .role(user.getRole())
        .gender(formData.getGender())
        .build();

        System.out.println(userBuild);

        return repository.save(userBuild);

    }
}
