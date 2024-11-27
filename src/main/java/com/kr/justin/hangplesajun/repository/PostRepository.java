package com.kr.justin.hangplesajun.repository;

import com.kr.justin.hangplesajun.domain.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, QueryPostRepository {
    Optional<Post> findByUserIdAndId(Long id, long userId);

    void deleteByUserIdAndId(long userId, long id);
}
