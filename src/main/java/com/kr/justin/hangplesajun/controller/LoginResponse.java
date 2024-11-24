package com.kr.justin.hangplesajun.controller;

public record LoginResponse(String msg, String statusCode) {

    public static LoginResponse success() {
        return new LoginResponse("로그인에 성공하였습니다", "200");
    }
}
