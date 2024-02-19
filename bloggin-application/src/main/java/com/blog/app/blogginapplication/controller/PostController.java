package com.blog.app.blogginapplication.controller;

import com.blog.app.blogginapplication.dto.PostDto;
import com.blog.app.blogginapplication.exception.ResourceNotFoundException;
import com.blog.app.blogginapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    // create Post REST API
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get post using id REST API
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // get all posts REST API
    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

    // update post using id REST API
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable(value = "id") Long id,
            @RequestBody PostDto postDto)
    {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    // delete post by id REST API
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable(value = "id") Long id){
        postService.deletePost(id);
        return "Post deleted successfully";
    }
}
