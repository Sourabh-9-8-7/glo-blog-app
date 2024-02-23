package com.blog.app.bloggingapplication.repository;

import com.blog.app.bloggingapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
    Boolean existsByPostId(Long postId);
}
