package com.skillshiring.demo.service;

import com.skillshiring.demo.Repository.CommentRepo;
import com.skillshiring.demo.Repository.PostRepo;
import com.skillshiring.demo.Repository.UserRepo;
import com.skillshiring.demo.models.Comment;
import com.skillshiring.demo.models.Post;
import com.skillshiring.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplement implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepository;

    @Override
    public Post createPost(Post post, Integer userId)  {

        User user=userService.findUserById(userId);
        Post newPost = new Post();
        newPost.setId(post.getId());
        newPost.setCaption(post.getCaption());
        newPost.setKeywords(post.getKeywords());
        newPost.setImg(post.getImg());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        newPost.setLikes(post.getLikes());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUpdatedAt(LocalDateTime.now());
        return postRepo.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId)  {
        Optional<Post> postOpt = postRepo.findById(postId);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            if (!post.getUser().getId().equals(userId)) {
                return "Unauthorized action. You can only delete your own posts.";
            }
            postRepo.deleteById(postId);
            return "Post deleted successfully.";
        }
        return "Post not found.";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {

        return postRepo.findPostBYUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt=postRepo.findById(postId);
       if(opt.isEmpty()){
           throw new Exception("post not found with id "+postId);
       }
        return opt.get();
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public Post savePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPosts().contains(post)) {
            user.getSavedPosts().remove(post);
        }
        else user.getSavedPosts().add(post);
        userRepo.save(user);
        return post;

    }

    @Override
    public Post updatePost(Integer postId, Integer userId,Post updatedPostData) throws Exception {
        Optional<Post> postOpt = postRepo.findById(postId);

        if (postOpt.isPresent()) {
            Post existingPost = postOpt.get();

            if (!existingPost.getUser().getId().equals(userId)) {
                throw new Exception("User not authorized to update this post");
            }

            // Only allow updating caption and keywords
            if (updatedPostData.getCaption() != null) {
                existingPost.setCaption(updatedPostData.getCaption());
            }
            if (updatedPostData.getKeywords() != null) {
                existingPost.setKeywords(updatedPostData.getKeywords());
            }

            existingPost.setUpdatedAt(java.time.LocalDateTime.now());

            return postRepo.save(existingPost);
        }

        throw new Exception("Post not found");
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new Exception("Post not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        // Check if user already liked the post
        if (!post.getLikes().contains(user)) {
            post.getLikes().add(user);
            postRepo.save(post);
        } else {
            throw new Exception("User already liked this post");
        }

        return post;
    }

    @Override
    public Post commentPost(Integer postId, Integer userId, Comment commentData) throws Exception {
        return null;
    }

//    @Override
//    public Post commentPost(Integer postId, Integer userId, Comment commentData) throws Exception {
//        Post post = findPostById(postId);
//        User user = userRepo.findById(userId)
//                .orElseThrow(() -> new Exception("User not found"));
//
//        commentData.setUser(user);
//        commentData.setPost(post);
//        commentRepository.save(commentData);
//        post.getComments().add(commentData);
//        return postRepo.save(post);
//    }

}
