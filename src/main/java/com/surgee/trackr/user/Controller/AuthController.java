package com.surgee.trackr.user.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.surgee.trackr.user.DTO.HttpResponseDTO;
// import com.surgee.trackr.user.DTO.UserRegistratio
import com.surgee.trackr.user.model.User;
import com.surgee.trackr.user.requests.HttpLoginRequest;
import com.surgee.trackr.user.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponseDTO> registerUser(@RequestBody User user) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        boolean success = false;
        try {
            if(user.getLastName() == null || user.getFirstName() == null) {
                status = HttpStatus.BAD_REQUEST;
                throw new IllegalArgumentException("First name and last name are required");
            }
            
            return authService.registerUser(user);
        } catch (Error err) {
            return new ResponseEntity<>(HttpResponseDTO.builder()
                .data(null)
                .error(err.getMessage())
                .message("Login failed")
                .success(success)
                .status(status)
                .build(), status);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HttpResponseDTO> loginUser(@RequestBody HttpLoginRequest request) {
        return authService.loginUser(request.getEmail(), request.getPassword());
    }
}
