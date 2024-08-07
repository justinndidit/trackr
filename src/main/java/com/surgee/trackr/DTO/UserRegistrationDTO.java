package com.surgee.trackr.DTO;

import com.surgee.trackr.model.Role;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRegistrationDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private Role role;
    private String tokenType;
    private String password;
}
