package com.skillshiring.demo.service;

import com.skillshiring.demo.models.Comment;
import com.skillshiring.demo.models.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post,Integer userId) throws Exception;

    String deletePost(Integer postId,Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId) ;

    Post findPostById(Integer postId) throws Exception;

    List<Post> findAllPosts();

    Post savePost(Integer postId,Integer userId) throws Exception;

    Post updatePost(Integer postId, Integer userId, Post updatedPostData) throws Exception;

    Post likePost(Integer postId,Integer userId) throws Exception;

    Post commentPost(Integer postId, Integer userId, Comment commentData) throws Exception;

}
