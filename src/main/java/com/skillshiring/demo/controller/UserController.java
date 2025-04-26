package com.skillshiring.demo.controller;

import com.skillshiring.demo.Repository.UserRepo;
import com.skillshiring.demo.models.User;
import com.skillshiring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping("/get-alluser")
    public List<User> getAllUser() {
        List<User> users=userRepo.findAll();
        return users;
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) {
        User user=userService.findUserById(id);
        return user;
    }


    @PutMapping("/update-user")
    public User updateUser(@RequestHeader("Authorization") String token,@RequestBody User user) {
        User reqUser=userService.findUserByJwtToken(token);
        User updateuser=userService.updateUser(user,reqUser.getId());
        return updateuser;
    }

    @PutMapping("/follow-user/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String token,@PathVariable Integer userId2) {
        User reqUser = userService.findUserByJwtToken(token);
        User user=userService.followUser(reqUser.getId(),userId2);
        return user;
    }

//    @PutMapping("/follow-user/{userId1}/{userId2}")
//    public User followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) {
//        User user=userService.followUser(userId1,userId2);
//        return user;
//    }

    @GetMapping("/search-user")
    public List<User> searchUser(@RequestParam ("query") String query) {
        List<User> users=userService.searchUser(query);
        return users;
    }


    @DeleteMapping("/delete-user/{userId}")
    public String deleteUser(@PathVariable Integer userId) {

        Optional<User> user=userRepo.findById(userId);

        if(user.isEmpty()) {
            try {
                throw new Exception("user not exit with id "+userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        userRepo.deleteById(userId);
        return "User deleted successfully with id "+userId;
    }

    @GetMapping("/user/profile")
    public User getUserWithToken(@RequestHeader("Authorization") String token) {
//        String email=
        System.out.println("jwt ---> "+token);
        User user=userService.findUserByJwtToken(token);
        user.setPassword(null);
        return user;
    }
}
