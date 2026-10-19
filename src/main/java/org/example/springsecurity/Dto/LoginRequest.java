package org.example.springsecurity.Dto;


import lombok.Data;

@Data
public class LoginRequest {

    String identifier;
    String password;
}
