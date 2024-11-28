package com.kr.justin.hangplesajun.controller;

import static com.kr.justin.hangplesajun.controller.PostResponse.from;

import com.kr.justin.hangplesajun.config.UserPrincipal;
import com.kr.justin.hangplesajun.domain.Post;
import com.kr.justin.hangplesajun.service.PostService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController implements PostControllerDocs {

    private final PostService postService;

    @PostMapping("/api/post")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public ResponseEntity<PostResponse> savePost(
            @RequestBody PostRequest request, @AuthenticationPrincipal UserPrincipal user) {

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
            @RequestParam(required = false, defaultValue = "DESC") SearchOrder order,
            @AuthenticationPrincipal UserPrincipal user) {
        List<Post> posts = postService.getAllBy(PostSearchQuery.of(user.getId(), title, content, createAt, order));
        return ResponseEntity.ok(PostsResponse.of(posts));
    }

    @GetMapping("/api/post/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public ResponseEntity<PostResponse> getPostDetail(
            @PathVariable Long id, @AuthenticationPrincipal UserPrincipal user) {

        if (user.isAdmin()) {
            Post post = postService.getAny(id);
            return ResponseEntity.ok(PostResponse.from(post));
        }

        Post post = postService.get(user.getId(), id);
        return ResponseEntity.ok(PostResponse.from(post));
    }

    @PatchMapping("/api/post/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id, @RequestBody PostRequest request, @AuthenticationPrincipal UserPrincipal user) {

        if (user.isAdmin()) {
            Post updatedPost = postService.updateAny(id, Post.of(user.getId(), request.title(), request.content()));
            return ResponseEntity.ok(PostResponse.from(updatedPost));
        }

        Post updatedPost = postService.update(id, Post.of(user.getId(), request.title(), request.content()));
        return ResponseEntity.ok(PostResponse.from(updatedPost));
    }

    @DeleteMapping("/api/post/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal user) {

        if (user.isAdmin()) {
            postService.deleteAny(id);
            return ResponseEntity.ok().build();
        }
        postService.delete(user.getId(), id);
        return ResponseEntity.ok().build();
    }
}
