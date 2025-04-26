package com.skillshiring.demo.controller;

import com.skillshiring.demo.models.Comment;
import com.skillshiring.demo.models.Post;
import com.skillshiring.demo.response.ApiResponse;
import com.skillshiring.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("create/post/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception {
        Post createdPost=postService.createPost(post,userId);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> getPost(@PathVariable Integer userId, @PathVariable Integer postId) throws Exception {

        String message=postService.deletePost(userId,postId);
        ApiResponse res=new ApiResponse(message,true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);

    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post=postService.findPostById(postId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/post/user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId)  {

        List<Post> posts=postService.findPostByUserId(userId);

        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {

        List<Post> posts=postService.findAllPosts();

        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @PutMapping("/post/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        Post post=postService.savePost(postId,userId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{postId}/user/{userId}")
    public Post updatePost(@PathVariable Integer postId, @PathVariable Integer userId, @RequestBody Post updatedPostData) throws Exception {
        return postService.updatePost(postId, userId, updatedPostData);
    }

    @PutMapping("/post/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        Post post=postService.likePost(postId,userId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }
    @PostMapping("/comment/{postId}/user/{userId}")
    public Post commentOnPost(@PathVariable Integer postId, @PathVariable Integer userId,
                              @RequestBody Comment comment) throws Exception {
        return postService.commentPost(postId, userId, comment);
    }
//    @GetMapping("post/{postId}/comments")
//    public List<Comment> getCommentsForPost(@PathVariable Integer postId) throws Exception {
//        Post post = postService.findPostById(postId);
//        return post.getComments();
//    }

}
