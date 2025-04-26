package com.skillshiring.demo.service;

import com.skillshiring.demo.Repository.UserRepo;
import com.skillshiring.demo.config.JwtProvider;
import com.skillshiring.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    UserRepo userRepo;


    @Override
    public User registerUser(User user) {

        User newUser=new User();

        newUser.setId(user.getId());
        newUser.setAvatar(user.getAvatar());
        newUser.setCover(user.getCover());
        newUser.setEmail(user.getEmail());
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setDescription(user.getDescription());
        newUser.setPassword(user.getPassword());
        newUser.setCity(user.getCity());
        newUser.setWork(user.getWork());
        newUser.setWebsite(user.getWebsite());
        newUser.setFollowers(user.getFollowers());
        newUser.setFollowing(user.getFollowing());


        User save;
        save = userRepo.save(newUser);

        return save;
    }

    @Override
    public User findUserById(Integer userId) {
        Optional<User> user =userRepo.findById(userId);

        if(user.isPresent()) {
            return user.get();
        }
        try {
            throw new Exception("user not exist with userid "+userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        User user=userRepo.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) {
        //req --> other
        User reqUser=findUserById(reqUserId);
        User user2=findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowing().add(user2.getId());

        userRepo.save(reqUser);
        userRepo.save(user2);
        return reqUser;
    }

    @Override
    public User unfollowUser(Integer userId1, Integer followerId) {
        return null;
    }

    @Override
    public User updateUser(User user,Integer userId) {
        Optional<User> user1=userRepo.findById(userId);

        if(user1.isEmpty()) {
            try {
                throw new Exception("user not exit with id "+userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        User newUser=user1.get();

        if(user.getAvatar() != null) {
            newUser.setAvatar(user.getAvatar());
        }
        if (user.getCover() != null) {
            newUser.setCover(user.getCover());
        }
        if (user.getFname() != null) {
            newUser.setFname(user.getFname());
        }
        if (user.getLname() != null) {
            newUser.setLname(user.getLname());
        }
        if (user.getEmail() != null) {
            newUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            newUser.setPassword(user.getPassword());
        }
        if(user.getDescription() != null) {
            newUser.setDescription(user.getDescription());
        }
        if(user.getCity() != null) {
            newUser.setCity(user.getCity());
        }
        if(user.getWebsite() != null) {
            newUser.setWebsite(user.getWebsite());
        }
        if(user.getWork() != null) {
            newUser.setWork(user.getWork());
        }
        if (user.getFollowers() != null) {
            newUser.setFollowers(user.getFollowers());
        }
        if (user.getFollowing() != null) {
            newUser.setFollowing(user.getFollowing());
        }



        User updatedUser;
        updatedUser = userRepo.save(newUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepo.searchUser(query);
    }

    @Override
    public User findUserByJwtToken(String jwtToken) {
        String email= JwtProvider.getEmailFromToken(jwtToken);
        User user=userRepo.findByEmail(email);
        return user;
    }
}
