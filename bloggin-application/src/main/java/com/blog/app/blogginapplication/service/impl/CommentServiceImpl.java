package com.blog.app.blogginapplication.service.impl;

import com.blog.app.blogginapplication.dto.CommentDto;
import com.blog.app.blogginapplication.entity.Comment;
import com.blog.app.blogginapplication.entity.Post;
import com.blog.app.blogginapplication.exception.BlogApiException;
import com.blog.app.blogginapplication.exception.ResourceNotFoundException;
import com.blog.app.blogginapplication.repository.CommentRepository;
import com.blog.app.blogginapplication.repository.PostRepository;
import com.blog.app.blogginapplication.service.CommentService;
import com.blog.app.blogginapplication.util.MapperUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Comment comment = MapperUtility.mapToComment(commentDto);
        comment.setPost(post);
        return MapperUtility.mapToCommentDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        if(!commentRepository.existsByPostId(postId))
            throw new ResourceNotFoundException("Comment","postId",postId);

        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(MapperUtility::mapToCommentDto).toList();
    }

    @Override
    public CommentDto getCommentById(Long postId, Long id) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", id)
        );

        if(!comment.getPost().getId().equals(post.getId()))
            throw new BlogApiException("Comment doesn't belong to the post");

        return MapperUtility.mapToCommentDto(comment);
    }

    @Override
    public CommentDto updateCommentById(Long postId, Long id, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", id)
        );

        if(!comment.getPost().getId().equals(post.getId()))
            throw new BlogApiException("Comment doesn't belong to the post");

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return MapperUtility.mapToCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long postId, Long id) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", id)
        );

        if(!comment.getPost().getId().equals(post.getId()))
            throw new BlogApiException("Comment doesn't belong to the post");

        commentRepository.delete(comment);
    }
}
