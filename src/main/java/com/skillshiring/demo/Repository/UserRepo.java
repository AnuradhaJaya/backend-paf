package com.skillshiring.demo.Repository;

import com.skillshiring.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByEmail(String email);

    @Query("select u from User u where u.fname like %:query% or u.lname like %:query% or u.email like %:query%")
    public List<User> searchUser(@Param("query") String query);

}
