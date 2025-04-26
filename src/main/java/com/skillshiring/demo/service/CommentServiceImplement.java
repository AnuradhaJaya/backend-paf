package com.skillshiring.demo.service;

import com.skillshiring.demo.Repository.CommentRepo;
import com.skillshiring.demo.models.Comment;
import com.skillshiring.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommentServiceImplement implements CommentService {


    @Override
    public List<Comment> getCommentsByPost(Integer postId) {
        return null;
    }
}
