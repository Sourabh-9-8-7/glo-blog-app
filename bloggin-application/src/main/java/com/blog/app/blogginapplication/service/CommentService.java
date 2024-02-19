package com.blog.app.blogginapplication.service;

import com.blog.app.blogginapplication.dto.CommentDto;
import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto getCommentById(Long postId, Long id);
    CommentDto updateCommentById(Long postId, Long id,CommentDto commentDto);
    void deleteComment(Long postId, Long id);
}
