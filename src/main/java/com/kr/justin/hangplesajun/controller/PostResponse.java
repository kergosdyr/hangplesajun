package com.kr.justin.hangplesajun.controller;

import java.time.LocalDateTime;

import com.kr.justin.hangplesajun.domain.Post;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PostResponse(
	Long id,
	String title,
	String content,
	LocalDateTime createdAt,
	LocalDateTime modifiedAt
) {
	public static PostResponse from(Post post) {
		return PostResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.createdAt(post.getCreatedAt())
			.modifiedAt(post.getModifiedAt())
			.build();
	}
}
