package com.blog.app.bloggingapplication.service;

import com.blog.app.bloggingapplication.dto.PostDto;
import com.blog.app.bloggingapplication.dto.PostResponse;

import java.util.List;

public interface PostService {
    // create a post
    PostDto createPost(PostDto postDto);

    // get a post using id
    PostDto getPostById(Long id);

    // update the post
    PostDto updatePost(long id, PostDto postDto);

    // get all posts
    PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    // delete post using id
    void deletePost(long id);
}
