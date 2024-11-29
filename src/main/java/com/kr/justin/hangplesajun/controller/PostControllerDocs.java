package com.kr.justin.hangplesajun.controller;

import com.kr.justin.hangplesajun.config.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "게시물 API", description = "게시물 관련 API")
@SecurityRequirement(name = "bearerAuth")
public interface PostControllerDocs {

    @Operation(
            summary = "게시물 생성",
            description = "새로운 게시물을 생성합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "게시물 생성 성공"),
                @ApiResponse(responseCode = "400", description = "잘못된 요청")
            })
    @PostMapping("/api/post")
    ResponseEntity<PostResponse> savePost(
            @RequestBody(description = "게시물 생성 요청", required = true) PostRequest request, UserPrincipal user);

    @Operation(
            summary = "게시물 목록 조회",
            description = "모든 게시물을 조회합니다.",
            responses = {@ApiResponse(responseCode = "200", description = "게시물 목록 조회 성공")})
    @GetMapping("/api/posts")
    ResponseEntity<PostsResponse> getPosts(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) LocalDateTime createAt,
            @RequestParam(required = false, defaultValue = "DESC") SearchOrder order,
            UserPrincipal user);

    @Operation(
            summary = "게시물 상세 조회",
            description = "ID를 사용하여 특정 게시물을 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "게시물 조회 성공"),
                @ApiResponse(responseCode = "404", description = "게시물을 찾을 수 없음")
            })
    @GetMapping("/api/post/{id}")
    ResponseEntity<PostResponse> getPostDetail(
            @Parameter(description = "게시물 ID", required = true, example = "1") @PathVariable Long id,
            UserPrincipal user);

    @Operation(
            summary = "게시물 수정",
            description = "ID를 사용하여 게시물을 수정합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "게시물 수정 성공"),
                @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                @ApiResponse(responseCode = "404", description = "게시물을 찾을 수 없음")
            })
    @PutMapping("/api/post/{id}")
    ResponseEntity<PostResponse> updatePost(
            @Parameter(description = "게시물 ID", required = true, example = "1") @PathVariable Long id,
            @RequestBody(description = "게시물 수정 요청", required = true) PostRequest request,
            UserPrincipal user);

    @Operation(
            summary = "게시물 삭제",
            description = "ID를 사용하여 게시물을 삭제합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "게시물 삭제 성공"),
                @ApiResponse(responseCode = "404", description = "게시물을 찾을 수 없음")
            })
    @DeleteMapping("/api/post/{id}")
    ResponseEntity<Void> deletePost(
            @Parameter(description = "게시물 ID", required = true, example = "1") @PathVariable Long id,
            UserPrincipal user);
}
