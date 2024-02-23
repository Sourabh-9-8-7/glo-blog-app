package com.blog.app.bloggingapplication.repository;

import com.blog.app.bloggingapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
