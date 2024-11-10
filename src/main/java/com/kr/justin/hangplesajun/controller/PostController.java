package com.kr.justin.hangplesajun.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController implements PostControllerDocs {

	@PostMapping("/api/post")
	@Override
	public ResponseEntity<PostResponse> getPost(
		PostRequest request

	) {
		return ResponseEntity.ok(null);
	}

	@GetMapping("/api/posts")
	@Override
	public ResponseEntity<PostsResponse> getPosts(

	) {
		return ResponseEntity.ok(null);
	}

	@GetMapping("/api/post/{id}")
	@Override
	public ResponseEntity<PostResponse> getPostDetail(
		@PathVariable Long id
	) {
		return ResponseEntity.ok(null);
	}

	@PutMapping("/api/post/{id}")
	@Override
	public ResponseEntity<PostResponse> updatePost(
		@PathVariable Long id,
		@RequestBody PostRequest request
	) {
		return ResponseEntity.ok(null);
	}

	@DeleteMapping("/api/post/{id}")
	@Override
	public ResponseEntity<PostResponse> deletePost(
		@PathVariable Long id
	) {
		return ResponseEntity.ok(null);
	}
}
