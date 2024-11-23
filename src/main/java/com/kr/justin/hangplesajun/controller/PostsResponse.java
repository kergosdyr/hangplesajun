package com.kr.justin.hangplesajun.controller;

import com.kr.justin.hangplesajun.domain.Post;
import java.util.List;

public record PostsResponse(List<PostResponse> postList) {
    public static PostsResponse of(List<Post> posts) {
        return new PostsResponse(posts.stream().map(PostResponse::from).toList());
    }
}
