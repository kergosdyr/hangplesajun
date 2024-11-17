package com.kr.justin.hangplesajun.controller;

import static com.kr.justin.hangplesajun.controller.LoginResponse.success;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kr.justin.hangplesajun.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

	private final AuthenticationManager authenticationManager;

	private final JwtUtil jwtUtil;

	@PostMapping("/api/auth/signup")
	@Override
	public ResponseEntity<SignUpResponse> signup(
		@RequestBody SignUpRequest request

	) {
		return ResponseEntity.ok(null);
	}

	@PostMapping("/api/auth/login")
	@Override
	public ResponseEntity<LoginResponse> login(
		@RequestBody LoginRequest request
	) {
		UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(
			request.username(), request.password());

		authenticationManager.authenticate(authRequest);

		return ResponseEntity.ok(success(jwtUtil.generateToken(request.username())));
	}

}
