package com.blog.app.blogginapplication.service.impl;

import com.blog.app.blogginapplication.dto.PostDto;
import com.blog.app.blogginapplication.entity.Post;
import com.blog.app.blogginapplication.exception.ResourceNotFoundException;
import com.blog.app.blogginapplication.repository.PostRepository;
import com.blog.app.blogginapplication.service.PostService;
import com.blog.app.blogginapplication.util.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = MapperUtility.mapToPost(postDto);
        Post created = postRepository.save(post);
        return MapperUtility.mapToPostDto(created);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        return MapperUtility.mapToPostDto(post);
    }

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(MapperUtility::mapToPostDto).toList();
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );

        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return MapperUtility.mapToPostDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        postRepository.delete(post);
    }
}
