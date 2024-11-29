package com.kr.justin.hangplesajun.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/user_data.sql", "/post_data.sql", "/reply_data.sql"}) // 테스트 전에 스키마 및 데이터 설정
class PostControllerTest extends WebIntegrationTest {

    @Test
    @DisplayName("게시글 목록 조회 성공 테스트")
    void testGetPostsList_Success() {
        givenAuth()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/posts")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("postList", hasSize(greaterThan(0))) // 게시글이 존재하는지 확인
                .body("postList[0].title", equalTo("First Post"))
                .body("postList[0].replies", hasSize(2)) // 댓글 개수 확인
                .body("postList[0].replies[0].content", equalTo("첫 번째 게시물에 대한 첫 번째 댓글"))
                .body("postList[0].replies[1].content", equalTo("첫 번째 게시물에 대한 두 번째 댓글"))
                .body("postList[0].createdAt", notNullValue());
    }

    @Test
    @DisplayName("게시글 생성 성공 테스트")
    void testCreatePost_Success() {
        givenAuth()
                .contentType(ContentType.JSON)
                .body(new PostRequest("Test Title", "Test Content"))
                .when()
                .post("/api/post")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("title", equalTo("Test Title"))
                .body("content", equalTo("Test Content"));
    }

    @Test
    @DisplayName("게시글 상세 조회 성공 테스트")
    void testGetPostDetail_Success() {
        givenAuth()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/post/10003")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("id", equalTo(10003))
                .body("title", equalTo("First Post"))
                .body("content", equalTo("This is the content of the first post"))
                .body("replies", hasSize(2)) // 댓글 개수 확인
                .body("replies[0].content", equalTo("첫 번째 게시물에 대한 첫 번째 댓글"))
                .body("replies[1].content", equalTo("첫 번째 게시물에 대한 두 번째 댓글"));
    }

    @Test
    @DisplayName("게시글 수정 성공 테스트")
    void testUpdatePost_Success() {
        givenAuth()
                .contentType(ContentType.JSON)
                .body(new PostRequest("Updated Title", "Updated Content"))
                .when()
                .patch("/api/post/10003")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .body("content", equalTo("Updated Content"));
    }

    @Test
    @DisplayName("게시글 삭제 성공 테스트")
    void testDeletePost_Success() {
        givenAuth()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/post/10003")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("게시글 삭제 실패 테스트")
    void testDeletePost_failed() {
        givenAuth()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/post/10004")
                .then()
                .statusCode(500)
                .body("statusCode", equalTo("500"))
                .body("msg", equalTo("서버 에러가 발생했습니다."));
    }

    @Test
    @DisplayName("게시글 수정 실패 테스트")
    void testUpdatePost_failed() {
        givenAuth()
                .contentType(ContentType.JSON)
                .body(new PostRequest("Updated Title", "Updated Content"))
                .when()
                .patch("/api/post/10004")
                .then()
                .statusCode(500)
                .body("statusCode", equalTo("500"))
                .body("msg", equalTo("서버 에러가 발생했습니다."));
    }

    @Test
    @DisplayName("관리자 권한 게시글 삭제 성공 테스트")
    void testDeletePost_admin_success() {
        givenAdminAuth()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/post/10003")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("관리자 권한 게시글 수정 성공 테스트")
    void testUpdatePost_admin_success() {
        givenAdminAuth()
                .contentType(ContentType.JSON)
                .body(new PostRequest("Updated Title", "Updated Content"))
                .when()
                .patch("/api/post/10003")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .body("content", equalTo("Updated Content"));
    }

    @Test
    @DisplayName("관리자 권한 게시글 조회 성공 테스트")
    void testGetDetailPostAdmin_success() {
        givenAdminAuth()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/post/10003")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("id", equalTo(10003))
                .body("title", equalTo("First Post"))
                .body("content", equalTo("This is the content of the first post"))
                .body("replies", hasSize(2)) // 댓글 개수 확인
                .body("replies[0].content", equalTo("첫 번째 게시물에 대한 첫 번째 댓글"))
                .body("replies[1].content", equalTo("첫 번째 게시물에 대한 두 번째 댓글"));
    }
}
