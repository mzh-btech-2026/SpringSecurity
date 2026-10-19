package org.example.springsecurity.repository;

import org.example.springsecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<User> findByUsernameOrEmailOrPhone(
            String username,
            String email,
            String phone);

    boolean findByUsername(String username);

}