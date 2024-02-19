package com.blog.app.blogginapplication.repository;

import com.blog.app.blogginapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);
    Boolean existsByPostId(long postId);
}
