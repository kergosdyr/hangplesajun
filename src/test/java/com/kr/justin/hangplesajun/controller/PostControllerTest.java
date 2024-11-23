package com.kr.justin.hangplesajun.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import io.restassured.http.ContentType;

@Sql({"/user_data.sql","/post_data.sql"}) // 테스트 전에 스키마 및 데이터 설정
class PostControllerTest extends WebIntegrationTest {

    @Test
    void testCreatePost_Success() {
        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + jwtToken)
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
    void testGetPosts_Success() {
        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + jwtToken)
            .when()
            .get("/api/posts")
            .prettyPeek()
            .then()
            .statusCode(200)
            .body("postList", hasSize(2))
            .body("postList.title", containsInAnyOrder("First Post", "Second Post"))
            .body("postList.content", containsInAnyOrder("This is the content of the first post", "This is the content of the second post"));

    }

    @Test
    void testGetPostDetail_Success() {
        given()
            .header("Authorization", "Bearer " + jwtToken)
            .contentType(ContentType.JSON)
            .when()
            .get("/api/post/3")
            .prettyPeek()
            .then()
            .statusCode(200)
            .body("id", equalTo(3));
    }

    @Test
    void testUpdatePost_Success() {
        given()
            .header("Authorization", "Bearer " + jwtToken)
            .contentType(ContentType.JSON)
            .body(new PostRequest("Updated Title", "Updated Content"))
            .when()
            .put("/api/post/3")
            .prettyPeek()
            .then()
            .statusCode(200)
            .body("title", equalTo("Updated Title"))
            .body("content", equalTo("Updated Content"));
    }

    @Test
    void testDeletePost_Success() {
        given()
            .header("Authorization", "Bearer " + jwtToken)
            .contentType(ContentType.JSON)
            .when()
            .delete("/api/post/3")
            .then()
            .statusCode(200);
    }
}