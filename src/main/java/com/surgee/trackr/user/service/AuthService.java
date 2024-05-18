package com.surgee.trackr.user.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.surgee.trackr.user.DAO.UserRepository;
import com.surgee.trackr.user.DTO.HttpResponseDTO;
import com.surgee.trackr.user.DTO.UserRegistrationDTO;
import com.surgee.trackr.user.model.User;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Builder
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<HttpResponseDTO> registerUser(User user) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        Boolean success = false;
        try{
            if(user.getEmail() == null || user.getPassword() == null || user.getFirstName() == null || user.getLastName() == null) {
                statusCode = HttpStatus.BAD_REQUEST;
                throw new IllegalStateException("All fields are required");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(user);
    
            String token = jwtService.generateToken(user);

            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            success = true;
    
            UserRegistrationDTO data =  UserRegistrationDTO.builder()
                   .email(user.getEmail())
                   .firstName(user.getFirstName())
                   .lastName(user.getLastName())
                   .id(user.getId())
                   .token(token)
                   .role(user.getRole())
                   .tokenType(token)
                   .password(user.getPassword())
                   .build();

            HttpResponseDTO response = HttpResponseDTO.builder()
                                   .data(data)
                                   .error(null)
                                   .message("Registration successful")
                                   .success(success)
                                   .status(statusCode)
                                   .build();
            return new ResponseEntity<>(response, statusCode);
        } catch(Error err) {
            return new ResponseEntity<>(HttpResponseDTO.builder()
                .data(null)
                .error(err.getMessage())
                .message(err.getMessage())
                .success(success)
                .status(statusCode)
                .build(), statusCode);
        }
    }

    public ResponseEntity<HttpResponseDTO> loginUser(String email, String password) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        Boolean success = false;
        try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        Optional<User> user = repository.findUserByEmail(email);

        if(user.get() == null) {
            statusCode = HttpStatus.NOT_FOUND;
            throw new IllegalStateException("User not found");
            
        }

        statusCode = HttpStatus.OK;
        success = true;

        String token = jwtService.generateToken(user.get());
        UserRegistrationDTO userData =  UserRegistrationDTO.builder()
               .email(user.get().getEmail())
               .firstName(user.get().getFirstName())
               .lastName(user.get().getLastName())
               .id(user.get().getId())
               .role(user.get().getRole())
               .token(token)
               .tokenType("Bearer")
               .build();

        HttpResponseDTO response = HttpResponseDTO.builder()
                                    .data(userData)
                                    .error(null)
                                    .message("Login successful")
                                    .success(success)
                                    .status(statusCode)
                                    .build();
        return new ResponseEntity<>(response, statusCode);  
       } catch (Error err) {
        return new ResponseEntity<>(HttpResponseDTO.builder()
                .data(null)
                .error(err.getMessage())
                .message(err.getMessage())
                .success(success)
                .status(statusCode)
                .build(), statusCode);
       }

    }
}
