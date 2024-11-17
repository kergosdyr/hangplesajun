package com.kr.justin.hangplesajun.controller;

import java.util.List;

public record PostsResponse(
	List<PostResponse> postList
) {
}
