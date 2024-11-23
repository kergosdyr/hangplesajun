package com.kr.justin.hangplesajun.controller;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PostSearchQuery(
	String title,
	String content,
	LocalDateTime createAt,
	SearchOrder searchOrder
) {

	public static PostSearchQuery of(
		String title,
		String content,
		LocalDateTime createAt,
		SearchOrder order) {
		return new PostSearchQuery(title, content, createAt, order);
	}


}
