package com.kr.justin.hangplesajun.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "인증 API", description = "사용자 인증을 위한 API")
public interface AuthControllerDocs {

    @Operation(
            summary = "회원 가입",
            description = "새로운 사용자를 등록합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "회원 가입 성공"),
                @ApiResponse(responseCode = "400", description = "잘못된 요청")
            })
    @PostMapping("/api/auth/signup")
    ResponseEntity<SignUpResponse> signup(@RequestBody(description = "회원가입 요청", required = true) SignUpRequest request);

    @Operation(
            summary = "로그인",
            description = "사용자를 인증하고 JWT 토큰을 반환합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "로그인 성공"),
                @ApiResponse(responseCode = "401", description = "잘못된 요청")
            })
    @PostMapping("/api/auth/login")
    ResponseEntity<LoginResponse> login(@RequestBody(description = "로그인 요청", required = true) LoginRequest request);
}
