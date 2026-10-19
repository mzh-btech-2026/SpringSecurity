package org.example.springsecurity.controller;


import org.example.springsecurity.Dto.LoginRequest;
import org.example.springsecurity.Dto.RegisterRequest;
import org.example.springsecurity.models.User;
import org.example.springsecurity.services.RegisterService;
import org.example.springsecurity.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthController {

    private final RegisterService registerService;
    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    AuthController(RegisterService registerService,AuthenticationManager authenticationManager,JwtUtil jwtUtil){

        this.registerService=registerService;
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
    }


    // register new users

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody RegisterRequest registerRequest){

        User user = registerService.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);


    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        try{

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getIdentifier(),loginRequest.getPassword())
            );


            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();

            String token = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.status(HttpStatus.OK).body(token);

        }
        catch (Exception e){

            throw e;
        }



    }


}
