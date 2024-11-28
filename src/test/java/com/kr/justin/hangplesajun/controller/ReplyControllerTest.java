package com.kr.justin.hangplesajun.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/user_data.sql", "/post_data.sql", "/reply_data.sql"})
class ReplyControllerTest extends WebIntegrationTest {

    @Test
    @DisplayName("댓글 작성 성공 테스트")
    void testCreateReply_Success() {
        givenAuth()
                .contentType(ContentType.JSON)
                .body(new ReplyRequest("테스트 댓글 내용"))
                .when()
                .post("/api/posts/10003/replies")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("content", equalTo("테스트 댓글 내용"))
                .body("createdAt", notNullValue());
    }

    @Test
    @DisplayName("댓글 수정 성공 테스트")
    void testUpdateReply_Success() {
        givenAuth()
                .contentType(ContentType.JSON)
                .body(new ReplyRequest("수정된 댓글 내용"))
                .when()
                .patch("/api/replies/10001")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("content", equalTo("수정된 댓글 내용"))
                .body("createdAt", notNullValue());
    }

    @Test
    @DisplayName("댓글 수정 실패 테스트")
    void testUpdateReply_failed() {
        givenAuth()
                .contentType(ContentType.JSON)
                .body(new ReplyRequest("수정된 댓글 내용"))
                .when()
                .patch("/api/replies/10003")
                .prettyPeek()
                .then()
                .statusCode(500)
                .body("statusCode", equalTo("500"))
                .body("msg", equalTo("서버 에러가 발생했습니다."));
    }

    @Test
    @DisplayName("댓글 삭제 성공 테스트")
    void testDeleteReplyAdmin_success() {
        givenAuth()
                .when()
                .delete("/api/replies/10001")
                .then()
                .statusCode(200)
                .body("statusCode", equalTo("200"))
                .body("msg", equalTo("댓글 삭제 성공"));
    }

    @Test
    @DisplayName("댓글 삭제 실패 테스트")
    void testDeleteReply_failed() {
        givenAuth()
                .when()
                .delete("/api/replies/10003")
                .then()
                .statusCode(500)
                .body("statusCode", equalTo("500"))
                .body("msg", equalTo("서버 에러가 발생했습니다."));
    }

    @Test
    @DisplayName("댓글 관리자 수정 테스트")
    void testUpdateReplyAdmin_Success() {
        givenAdminAuth()
                .contentType(ContentType.JSON)
                .body(new ReplyRequest("수정된 댓글 내용"))
                .when()
                .patch("/api/replies/10001")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("content", equalTo("수정된 댓글 내용"))
                .body("createdAt", notNullValue());
    }

    @Test
    @DisplayName("댓글 관리자 삭제 테스트")
    void testDeleteReply_Success() {
        givenAdminAuth()
                .when()
                .delete("/api/replies/10001")
                .then()
                .statusCode(200)
                .body("statusCode", equalTo("200"))
                .body("msg", equalTo("댓글 삭제 성공"));
    }
}
