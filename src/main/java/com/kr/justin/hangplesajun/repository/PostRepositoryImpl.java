package com.kr.justin.hangplesajun.repository;

import java.util.List;

import com.kr.justin.hangplesajun.controller.PostSearchQuery;
import com.kr.justin.hangplesajun.domain.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements QueryPostRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Post> getAllBy(PostSearchQuery query) {

		return List.of();
	}
}
