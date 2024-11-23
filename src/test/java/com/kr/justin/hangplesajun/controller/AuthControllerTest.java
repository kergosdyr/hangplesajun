package com.kr.justin.hangplesajun.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import io.restassured.http.ContentType;

@Sql({"/user_data.sql"}) // 테스트 전에 스키마 및
class AuthControllerTest extends WebIntegrationTest {

	@Test
	void testSignup_Success() {
		given()
			.contentType(ContentType.JSON)
			.body(new SignUpRequest("user", "password"))
			.when()
			.post("/api/auth/signup")
			.then()
			.statusCode(200)
			.body("statusCode", equalTo("200"));
	}

	@Test
	void testLogin_Success() {
		given()
			.contentType(ContentType.JSON)
			.body(new LoginRequest("user1", "password1"))
			.when()
			.post("/api/auth/login")
			.then()
			.statusCode(200)
			.body("statusCode", equalTo("200"));
	}

	@Test
	void testLogin_InvalidCredentials() {
		given()
			.contentType(ContentType.JSON)
			.body(new LoginRequest("user", "wrongpassword"))
			.when()
			.post("/api/auth/login")
			.then()
			.statusCode(403);
	}

	@Test
	void testLogin_MissingCredentials() {
		given()
			.contentType(ContentType.JSON)
			.body(new LoginRequest("", ""))
			.when()
			.post("/api/auth/login")
			.then()
			.statusCode(403);
	}

}