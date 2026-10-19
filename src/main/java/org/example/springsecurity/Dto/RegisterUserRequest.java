package org.example.springsecurity.Dto;

import lombok.Data;
import org.example.springsecurity.models.Role;


@Data
public class RegisterUserRequest {

    private String username;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
