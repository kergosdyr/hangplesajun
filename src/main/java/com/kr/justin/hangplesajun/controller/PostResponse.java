package com.kr.justin.hangplesajun.controller;

import java.time.LocalDateTime;

public record PostResponse(
	Long id,
	String title,
	String content,
	String username,
	LocalDateTime createdAt,
	LocalDateTime modifiedAt
) {
}
