package com.skillshiring.demo.controller;

import com.skillshiring.demo.models.Comment;
import com.skillshiring.demo.models.Post;
import com.skillshiring.demo.models.User;
import com.skillshiring.demo.response.ApiResponse;
import com.skillshiring.demo.service.PostService;
import com.skillshiring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String token,@RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwtToken(token);
        Post createdPost=postService.createPost(post,reqUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> getPost(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws Exception {
        User reqUser = userService.findUserByJwtToken(token);
        String message=postService.deletePost(postId,reqUser.getId());
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

    @PutMapping("/post/save/{postId}")
    public ResponseEntity<Post> savePostHandler(@RequestHeader("Authorization") String token,@PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJwtToken(token);
        Post post=postService.savePost(postId,reqUser.getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{postId}/user/{userId}")
    public Post updatePost(@PathVariable Integer postId, @PathVariable Integer userId, @RequestBody Post updatedPostData) throws Exception {
        return postService.updatePost(postId, userId, updatedPostData);
    }

    @PutMapping("/post/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization") String token,@PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJwtToken(token);
        Post post=postService.likePost(postId,reqUser.getId());

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
