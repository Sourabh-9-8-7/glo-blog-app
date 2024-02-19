package com.blog.app.blogginapplication.service;

import com.blog.app.blogginapplication.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto getPostById(Long id);
    List<PostDto> getPosts();
    PostDto updatePost(Long id, PostDto postDto);
    void deletePost(Long id);
}
