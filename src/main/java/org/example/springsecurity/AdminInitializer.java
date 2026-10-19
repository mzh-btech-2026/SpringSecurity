package org.example.springsecurity;

import org.example.springsecurity.models.Role;
import org.example.springsecurity.models.User;
import org.example.springsecurity.repository.UsersRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(
            UsersRepo usersRepo,
            PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        String email = "admin@example.com";

        // Don't create the admin again if it already exists
        if (usersRepo.existsByEmail(email)) {
            System.out.println("Admin already exists.");
            return;
        }

        User admin = new User();

        admin.setUsername("admin");
        admin.setEmail(email);
        admin.setPhone("9876543210");

        // Encode the password
        admin.setPassword(
                passwordEncoder.encode("Admin@123456")
        );

        // Give this account the ADMIN role
        admin.setRole(Role.ADMIN);

        usersRepo.save(admin);

        System.out.println("Admin account created successfully!");
    }
}
