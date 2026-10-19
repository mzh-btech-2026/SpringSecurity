package org.example.springsecurity.services;


import org.example.springsecurity.repository.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepo usersRepo;

    CustomUserDetailService(UsersRepo usersRepo){
        this.usersRepo=usersRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        return usersRepo.findByUsernameOrEmailOrPhone(
                identifier,identifier,identifier
        ).orElseThrow(()-> new UsernameNotFoundException("no user found"));
    }
}
