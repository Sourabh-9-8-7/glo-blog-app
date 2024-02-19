package com.blog.app.blogginapplication.util;

import com.blog.app.blogginapplication.dto.CommentDto;
import com.blog.app.blogginapplication.dto.PostDto;
import com.blog.app.blogginapplication.entity.Comment;
import com.blog.app.blogginapplication.entity.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class MapperUtility {

    @Bean
    static ModelMapper modelMapper(){
        return new ModelMapper();
    }

    // map to PostDto class
    public static PostDto mapToPostDto(Post post){
        return modelMapper().map(post, PostDto.class);
    }

    // map to Post entity class
    public static Post mapToPost(PostDto postDto){
        return modelMapper().map(postDto, Post.class);
    }

    // map to CommentDto class
    public static CommentDto mapToCommentDto(Comment comment){
        return modelMapper().map(comment, CommentDto.class);
    }

    // map to Comment entity class
    public static Comment mapToComment(CommentDto commentDto){
        return modelMapper().map(commentDto,Comment.class);
    }
}
