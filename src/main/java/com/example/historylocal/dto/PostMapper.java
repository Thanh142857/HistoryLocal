package com.example.historylocal.dto;

import com.example.historylocal.entity.Post;

public class PostMapper {
    public static SearchDTO toSearchDTO(Post post) {
        return new SearchDTO(
                post.getId(),
                post.getTitle(),
                post.getLocation()
        );
    }

    public static PostResponse toPostResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getLocation(),
                post.getContent(),
                post.getCreatedAt(),
                post.getEventDate()
        );
    }
}
