package com.kr.justin.hangplesajun.controller;

import static com.kr.justin.hangplesajun.controller.PostResponse.from;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kr.justin.hangplesajun.config.UserPrincipal;
import com.kr.justin.hangplesajun.domain.Post;
import com.kr.justin.hangplesajun.domain.User;
import com.kr.justin.hangplesajun.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController implements PostControllerDocs {

	private final PostService postService;

	@PostMapping("/api/post")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public ResponseEntity<PostResponse> getPost(
		@RequestBody PostRequest request,
		@AuthenticationPrincipal(errorOnInvalidType = true) UserPrincipal user
	) {

		Post post = postService.create(Post.of(user.getId(), request.title(), request.content()));

		return ResponseEntity.ok(from(post));
	}

	@GetMapping("/api/posts")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public ResponseEntity<PostsResponse> getPosts(
		@RequestParam(required = false) String title,
		@RequestParam(required = false) String content,
		@RequestParam(required = false) LocalDateTime createAt,
		@RequestParam(required = false, defaultValue = "DESC") SearchOrder order
	) {
		List<Post> posts = postService.getAllBy(PostSearchQuery.of(title, content, createAt, order));
		return ResponseEntity.ok(PostsResponse.of(posts));
	}

	@GetMapping("/api/post/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public ResponseEntity<PostResponse> getPostDetail(
		@PathVariable Long id
	) {
		Post post = postService.get(id);
		return ResponseEntity.ok(PostResponse.from(post));
	}

	@PutMapping("/api/post/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public ResponseEntity<PostResponse> updatePost(
		@PathVariable Long id,
		@RequestBody PostRequest request,
		@AuthenticationPrincipal UserPrincipal user
	) {
		Post updatedPost = postService.update(id, Post.of(user.getId(), request.title(), request.content()));
		return ResponseEntity.ok(PostResponse.from(updatedPost));
	}

	@DeleteMapping("/api/post/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public ResponseEntity<Void> deletePost(
		@PathVariable Long id
	) {
		postService.delete(id);
		return ResponseEntity.ok().build();
	}

}
