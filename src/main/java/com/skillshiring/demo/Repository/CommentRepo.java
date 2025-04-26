package com.skillshiring.demo.Repository;

import com.skillshiring.demo.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c where c.post.id=:postId")
    List<Comment> findByPostId(Integer postId); // Spring will auto-implement this

}
