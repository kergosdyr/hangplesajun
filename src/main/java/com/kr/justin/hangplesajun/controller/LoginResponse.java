package com.kr.justin.hangplesajun.controller;

public record LoginResponse(
	String msg,
	String token,
	String statusCode
) {

	public static LoginResponse success(String token) {
		return new LoginResponse(
			"로그인에 성공하였습니다",
			token,
			"200"
		);

	}
}
