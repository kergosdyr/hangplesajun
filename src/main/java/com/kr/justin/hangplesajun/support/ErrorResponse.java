package com.kr.justin.hangplesajun.support;

public record ErrorResponse(String statusCode, String msg) {

    public static ErrorResponse of(String statusCode, String msg) {
        return new ErrorResponse(statusCode, msg);
    }

    public static ErrorResponse tokenInvalid() {
        return new ErrorResponse("400", "토큰이 유효하지 않습니다.");
    }

    public static ErrorResponse unauthorizedAction() {
        return new ErrorResponse("400", "작성자만 삭제/수정할 수 있습니다.");
    }

    public static ErrorResponse methodArgumentInvalid(String msg) {
        return new ErrorResponse("400", msg);
    }

    public static ErrorResponse usernameDuplicated() {
        return new ErrorResponse("400", "중복된 username 입니다.");
    }

    public static ErrorResponse memberNotFound() {
        return new ErrorResponse("400", "회원을 찾을 수 없습니다.");
    }

    public static ErrorResponse serverError() {
        return new ErrorResponse("500", "서버 에러가 발생했습니다.");
    }
}
