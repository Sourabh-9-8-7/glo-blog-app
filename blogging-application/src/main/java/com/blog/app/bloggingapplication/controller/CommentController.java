package com.blog.app.bloggingapplication.controller;

import com.blog.app.bloggingapplication.dto.CommentDto;
import com.blog.app.bloggingapplication.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // create comment REST API
    // localhost:8080/api/posts/{postId}/comments
    // implement the validation for the comments
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") Long postId,
            @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    // get comments using post id
    // localhost:8080/api/posts/{postId}/comments
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> findByPostId(
            @PathVariable(value = "postId") Long postId
    ){
        return ResponseEntity.ok(commentService.findByPostId(postId));
    }

    // find by comment id REST API
    // localhost:8080/api/posts/{postId}/comments/{commentId}
    // write the logic to implement this api

    // delete comment REST API
    // localhost:8080/api/posts/{postId}/comments/{commentId}
    // write the logic to implement this api

    // get all comments REST API
    // pagination and sorting feature

    // update comment by id REST API
    // validation using Validation dependency

}
