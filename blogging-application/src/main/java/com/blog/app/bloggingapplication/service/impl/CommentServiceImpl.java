package com.blog.app.bloggingapplication.service.impl;

import com.blog.app.bloggingapplication.dto.CommentDto;
import com.blog.app.bloggingapplication.entity.Comment;
import com.blog.app.bloggingapplication.entity.Post;
import com.blog.app.bloggingapplication.exception.BlogApiException;
import com.blog.app.bloggingapplication.exception.ResourceNotFoundException;
import com.blog.app.bloggingapplication.repository.CommentRepository;
import com.blog.app.bloggingapplication.repository.PostRepository;
import com.blog.app.bloggingapplication.service.CommentService;
import com.blog.app.bloggingapplication.utils.MapperUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        // check if valid return post if not throw exception
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        // save the comment into the comment entity by updating the post
        Comment comment = MapperUtility.mapToComment(commentDto);
        comment.setPost(post);
        Comment created = commentRepository.save(comment);
        return MapperUtility.mapToCommentDto(created);
    }

    @Override
    public List<CommentDto> findByPostId(Long postId) {
        // check if valid return post if not throw exception
        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        // check comment by post id
        if(!commentRepository.existsByPostId(postId))
            throw new BlogApiException("No comments found for the post id: " + postId);

        // get the comments using the post id
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(MapperUtility::mapToCommentDto).toList();
    }
}
