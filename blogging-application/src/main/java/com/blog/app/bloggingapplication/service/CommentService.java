package com.blog.app.bloggingapplication.service;

import com.blog.app.bloggingapplication.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> findByPostId(Long postId);
}
