package com.kr.justin.hangplesajun.controller;

public record SignUpResponse(String msg, String statusCode) {

    public static SignUpResponse success() {
        return new SignUpResponse("회원가입에 성공했습니다", "200");
    }
}
