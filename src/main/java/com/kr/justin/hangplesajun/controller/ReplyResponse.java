package com.kr.justin.hangplesajun.controller;

import com.kr.justin.hangplesajun.domain.Reply;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReplyResponse(String content, LocalDateTime createdAt) {
    public static ReplyResponse from(Reply reply) {

        return ReplyResponse.builder()
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }
}
