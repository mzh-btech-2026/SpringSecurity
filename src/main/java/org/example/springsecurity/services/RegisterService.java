package org.example.springsecurity.services;


import org.example.springsecurity.Dto.RegisterRequest;
import org.example.springsecurity.models.User;
import org.example.springsecurity.repository.UsersRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;

    RegisterService(UsersRepo usersRepo,PasswordEncoder passwordEncoder){
        this.usersRepo=usersRepo;
        this.passwordEncoder=passwordEncoder;
    }




    public User register(RegisterRequest registerRequest){

        if(usersRepo.existsByEmail(registerRequest.getEmail())){

            throw new IllegalArgumentException(
                    "Email already Exists"
            );
        }

        if(usersRepo.existsByUsername(registerRequest.getUsername())){

            throw new IllegalArgumentException(
                    "Username already Exists"
            );
        }

        if(usersRepo.existsByPhone(registerRequest.getPhone())){

            throw new IllegalArgumentException(
                    "Phone Number already Exists"
            );
        }

        // Convert the DTO into a Users entity
        User user = new User();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());

        // Encode the password before saving
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword()));

        // Save the user in the database
        return usersRepo.save(user);




    }
}
