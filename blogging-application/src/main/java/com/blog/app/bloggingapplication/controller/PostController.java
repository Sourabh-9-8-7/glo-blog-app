package com.blog.app.bloggingapplication.controller;

import com.blog.app.bloggingapplication.dto.PostDto;
import com.blog.app.bloggingapplication.dto.PostResponse;
import com.blog.app.bloggingapplication.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    // create post REST API
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get post using id REST API
    // URI Template variable or path variable
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(
            @PathVariable(value = "id") long id
    ){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update post using id REST API
    // validation for the update post
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable(value = "id") long id,
            @RequestBody PostDto postDto
    ){
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    // create getPosts REST API
    @GetMapping
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(value = "pageNo",defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        return ResponseEntity.ok(postService.getPosts(pageNo,pageSize,sortBy,sortDir));
    }

    // delete post using id REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable(value = "id") long id
    ){
        postService.deletePost(id);
        return ResponseEntity.ok("Post got deleted successfully with id :: "+id);
    }

}