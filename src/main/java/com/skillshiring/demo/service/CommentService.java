package com.skillshiring.demo.service;

import com.skillshiring.demo.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByPost(Integer postId) throws Exception;

}
