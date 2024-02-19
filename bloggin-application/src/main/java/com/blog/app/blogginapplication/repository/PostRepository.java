package com.blog.app.blogginapplication.repository;

import com.blog.app.blogginapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
