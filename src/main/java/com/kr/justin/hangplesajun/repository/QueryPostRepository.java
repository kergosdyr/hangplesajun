package com.kr.justin.hangplesajun.repository;

import com.kr.justin.hangplesajun.controller.PostSearchQuery;
import com.kr.justin.hangplesajun.domain.Post;
import java.util.List;

public interface QueryPostRepository {

    List<Post> getAllBy(PostSearchQuery query);
}
