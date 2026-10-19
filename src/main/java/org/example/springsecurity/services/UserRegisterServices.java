package org.example.springsecurity.services;


import org.example.springsecurity.Dto.RegisterUserRequest;
import org.example.springsecurity.models.User;
import org.example.springsecurity.repository.UsersRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServices {

    private final UsersRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    UserRegisterServices( UsersRepo usersRepo,PasswordEncoder passwordEncoder){
        this.userRepo=usersRepo;
        this.passwordEncoder=passwordEncoder;
    }

    public User registerUsers(RegisterUserRequest registerUserRequest ){



        if (userRepo.findByUsername(registerUserRequest.getUsername())) {

            throw new RuntimeException("alreay user exists");

        }


        User user = new User();

        user.setUsername(registerUserRequest.getUsername());
        user.setEmail(registerUserRequest.getEmail());
        user.setPhone(registerUserRequest.getPhone());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setRole(registerUserRequest.getRole());
        return userRepo.save(user);


    }
}
