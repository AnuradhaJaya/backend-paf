package com.skillshiring.demo.controller;

import com.skillshiring.demo.Repository.UserRepo;
import com.skillshiring.demo.config.JwtProvider;
import com.skillshiring.demo.models.User;
import com.skillshiring.demo.request.LoginRequest;
import com.skillshiring.demo.response.AuthResponse;
import com.skillshiring.demo.service.CustomerUserDetailsService;
import com.skillshiring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) {

        User isExist = userRepo.findByEmail(user.getEmail());
        if (isExist != null) {
            try {
                throw new Exception("this email already exists");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        User newUser=new User();

        newUser.setId(user.getId());
        newUser.setAvatar(user.getAvatar());
        newUser.setCover(user.getCover());
        newUser.setEmail(user.getEmail());
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setDescription(user.getDescription());
        newUser.setCity(user.getCity());
        newUser.setWork(user.getWork());
        newUser.setWebsite(user.getWebsite());
        newUser.setFollowers(user.getFollowers());
        newUser.setFollowing(user.getFollowing());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));



        User saveUser;
        saveUser = userRepo.save(newUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(saveUser.getEmail(),saveUser.getPassword());
        String token = JwtProvider.generateToken(auth);
        AuthResponse res;
        res = new AuthResponse(token,"Register Success.");

        return res;
    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
        Authentication auth = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(auth);
        AuthResponse res;
        res = new AuthResponse(token,"Login Success.");

        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("invalid email");

        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid email or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
    }
}
