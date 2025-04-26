package com.skillshiring.demo.service;

import com.skillshiring.demo.Repository.UserRepo;
import com.skillshiring.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("USER NOT FOUND WITH EMAIL: " + username);
        }

        // Add at least one authority (role)
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // If your User model has roles, you could use those instead:
        // if (user.getRoles() != null) {
        //     user.getRoles().forEach(role -> {
        //         authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        //     });
        // }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}