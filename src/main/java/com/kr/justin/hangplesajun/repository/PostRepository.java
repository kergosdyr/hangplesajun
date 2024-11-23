package com.kr.justin.hangplesajun.repository;

import com.kr.justin.hangplesajun.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, QueryPostRepository {}
