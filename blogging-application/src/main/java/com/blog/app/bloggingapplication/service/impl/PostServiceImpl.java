package com.blog.app.bloggingapplication.service.impl;

import com.blog.app.bloggingapplication.dto.PostDto;
import com.blog.app.bloggingapplication.dto.PostResponse;
import com.blog.app.bloggingapplication.entity.Post;
import com.blog.app.bloggingapplication.exception.ResourceNotFoundException;
import com.blog.app.bloggingapplication.repository.PostRepository;
import com.blog.app.bloggingapplication.service.PostService;
import com.blog.app.bloggingapplication.utils.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        // convert the dto to entity
        Post post = MapperUtility.mapToPost(postDto);
        // save the data into the entity
        Post created = postRepository.save(post);
        // convert the entity to dto and will send the data to the user
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
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );

//        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updated = postRepository.save(post);
        return MapperUtility.mapToPostDto(updated);
    }

    @Override
    public PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> all = postRepository.findAll(pageable);
        List<PostDto> postDtos = all.stream().map(MapperUtility::mapToPostDto).toList();

        PostResponse postResponse = new PostResponse();;
        postResponse.setContents(postDtos);
        postResponse.setPageNo(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalPages(all.getTotalPages());
        postResponse.setTotalElements(all.getTotalElements());
        postResponse.setLast(all.isLast());

        return postResponse;
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        postRepository.delete(post);
    }
}
