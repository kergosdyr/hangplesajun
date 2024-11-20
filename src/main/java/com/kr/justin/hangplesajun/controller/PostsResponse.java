package com.kr.justin.hangplesajun.controller;

import java.util.List;

import com.kr.justin.hangplesajun.domain.Post;

public record PostsResponse(
	List<PostResponse> postList
) {
	public static PostsResponse of(List<Post> posts) {
		return new PostsResponse(posts.stream().map(PostResponse::from).toList());
	}
}
