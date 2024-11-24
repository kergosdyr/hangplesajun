package com.kr.justin.hangplesajun.controller;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReplyDeleteResponse(String statusCode, String msg) {
    public static ReplyDeleteResponse success() {
        return ReplyDeleteResponse.builder().statusCode("200").msg("댓글 삭제 성공").build();
    }
}
