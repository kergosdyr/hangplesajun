package com.kr.justin.hangplesajun.repository;

import java.util.List;

import com.kr.justin.hangplesajun.controller.PostSearchQuery;
import com.kr.justin.hangplesajun.domain.Post;

public interface QueryPostRepository {

	List<Post> getAllBy(PostSearchQuery query);

}
