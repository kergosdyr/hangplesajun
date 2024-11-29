package com.kr.justin.hangplesajun.repository;

import com.kr.justin.hangplesajun.domain.Reply;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByIdAndUserId(long id, long userId);
}
