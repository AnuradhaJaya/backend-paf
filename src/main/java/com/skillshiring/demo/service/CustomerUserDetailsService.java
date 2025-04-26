package com.skillshiring.demo.service;

import com.skillshiring.demo.Repository.UserRepo;
import com.skillshiring.demo.models.User; // your domain model
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        User user = userRepo.findByEmail(username); // assuming you're searching by email

        if (user == null) {
            throw new UsernameNotFoundException("USER NOT FOUND WITH EMAIL: " + username);
        }

        // For now, grant a default role (e.g., ROLE_USER)
        List<GrantedAuthority> authorities = new ArrayList<>();



        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
