package com.kr.justin.hangplesajun.controller;

import com.kr.justin.hangplesajun.domain.User;
import com.kr.justin.hangplesajun.service.UserService;
import com.kr.justin.hangplesajun.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtil jwtUtil;

    @PostMapping("/api/auth/signup")
    @Override
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignUpRequest request) {

        userService.signup(User.of(request.username(), request.password()));

        return ResponseEntity.ok(SignUpResponse.success());
    }

    @PostMapping("/api/auth/login")
    @Override
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var authRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.username(), request.password());

        authenticationManager.authenticate(authRequest);

        var token = jwtUtil.generateToken(request.username());

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body(LoginResponse.success());
    }
}
