package org.example.springsecurity.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/security")
public class HelloController {


    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('READ')")
    public String sayHello(){

        return "Hello World";
    }

    @PostMapping("/hello")
    @PreAuthorize("hasAuthority('WRITE')")
    public String sayHelloPost() {
        return "Hello World from POST";
    }
}
