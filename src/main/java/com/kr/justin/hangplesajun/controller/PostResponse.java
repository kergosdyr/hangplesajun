package com.kr.justin.hangplesajun.controller;

import com.kr.justin.hangplesajun.domain.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PostResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        List<ReplyResponse> replies) {
    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .replies(post.getReplies().stream()
                        .map(ReplyResponse::from)
                        .sorted((r1, r2) -> r2.createdAt().compareTo(r1.createdAt()))
                        .toList())
                .build();
    }
}
