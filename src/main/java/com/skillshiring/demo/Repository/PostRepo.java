package com.skillshiring.demo.Repository;

import com.skillshiring.demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    @Query("select p from Post p where p.user.id=:userId")
    List<Post> findPostBYUserId(Integer userId);


}
