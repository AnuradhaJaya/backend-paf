package com.skillshiring.demo.controller;

import com.skillshiring.demo.models.Comment;
import com.skillshiring.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsForPost(@PathVariable Integer postId) throws Exception {
        return commentService.getCommentsByPost(postId);
    }
}
