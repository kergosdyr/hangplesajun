package com.kr.justin.hangplesajun.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import io.restassured.http.ContentType;

@Sql({"/user_data.sql", "/post_data.sql", "/reply_data.sql"})
class ReplyControllerTest extends WebIntegrationTest {

    @Test
    @DisplayName("댓글 작성 API - 성공 케이스")
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
    @DisplayName("댓글 수정 API - 성공 케이스")
    void testUpdateReply_Success() {
        givenAuth()
                .contentType(ContentType.JSON)
                .body(new ReplyRequest("수정된 댓글 내용"))
                .when()
                .put("/api/replies/10001")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("content", equalTo("수정된 댓글 내용"))
                .body("createdAt", notNullValue());
    }

    @Test
    @DisplayName("댓글 삭제 API - 성공 케이스")
    void testDeleteReply_Success() {
        givenAuth()
                .when()
                .delete("/api/replies/10001")
                .then()
                .statusCode(200)
                .body("statusCode", equalTo("200"))
                .body("msg", equalTo("댓글 삭제 성공"));
    }
}
