package com.blog.app.bloggingapplication.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostDto {
    private Long id;
    //title should not be null or empty
    //title should have minimum 2 characters
    @NotEmpty
    @Size(min = 2,message = "Post title should have 2 characters minimum")
    private String title;
    //description should have at least 10 characters
    //description should not be null or empty
    @NotEmpty
    @Size(min = 10,message = "Post description should have 10 characters minimum")
    private String description;
    //content should not be empty
    // 255 characters we have in string
    // 1000 words of blog post is a good length
    @NotBlank
    private String content;
}
