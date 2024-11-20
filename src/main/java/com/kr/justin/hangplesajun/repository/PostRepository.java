package com.kr.justin.hangplesajun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kr.justin.hangplesajun.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>, QueryPostRepository {
}
