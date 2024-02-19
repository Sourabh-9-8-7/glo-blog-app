package com.blog.app.blogginapplication.controller;

import com.blog.app.blogginapplication.dto.CommentDto;
import com.blog.app.blogginapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // create comment for post REST API
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") long postId,
            @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    // get comments using postId REST API
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(
            @PathVariable(value = "postId") long postId
    ){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    // get comments using id REST API
    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long id
    ){
        return ResponseEntity.ok(commentService.getCommentById(postId,id));
    }

    // update comment REST API
    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long id,
            @RequestBody CommentDto commentDto
    ){
        return ResponseEntity.ok(commentService.updateCommentById(postId,id,commentDto));
    }

    // delete comment REST API
    @DeleteMapping("/{postId}/comments/{id}")
    public String deleteComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long id
    ){
        commentService.deleteComment(postId,id);
        return "Comment deleted successfully for the post :: "+postId;
    }
}
