package com.blog.app.blogginapplication.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private List<CommentDto> comments;
}
