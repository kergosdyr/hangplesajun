package com.kr.justin.hangplesajun.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/user_data.sql"})
class AuthControllerTest extends WebIntegrationTest {

    @Test
    @DisplayName("가입된 고객에 대해서 ID, PW 가 맞다면 로그인 된다.")
    void testLogin_Success() {
        given().contentType(ContentType.JSON)
                .body(new LoginRequest("user1", "password1"))
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("statusCode", equalTo("200"));
    }

    @Test
    @DisplayName("username은 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 한다.")
    void testUsernameValidation() {
        given().contentType(ContentType.JSON)
                .body(new SignUpRequest("us1", "password12!"))
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(400);

        given().contentType(ContentType.JSON)
                .body(new SignUpRequest("thisisaverylongusername", "password12!"))
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(400);

        given().contentType(ContentType.JSON)
                .body(new SignUpRequest("validuser", "Password12!"))
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("password는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야 한다.")
    void testPasswordValidation() {
        given().contentType(ContentType.JSON)
                .body(new SignUpRequest("validuser", "short"))
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(400);

        given().contentType(ContentType.JSON)
                .body(new SignUpRequest("validuser", "toolongpasswordtoolong!1"))
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(400);

        given().contentType(ContentType.JSON)
                .body(new SignUpRequest("validuser", "Valid123!"))
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("DB에 중복된 username이 없다면 회원을 저장하고 Client로 성공했다는 메시지, 상태코드 반환하기")
    void testSignup_Success() {
        given().contentType(ContentType.JSON)
                .body(new SignUpRequest("newuser", "Valid123!"))
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(200)
                .body("statusCode", equalTo("200"));
    }

    @Test
    @DisplayName("로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 회원을 찾을 수 없습니다.")
    void testLogin_InvalidCredentials() {
        given().contentType(ContentType.JSON)
                .body(new LoginRequest("usernamenotfound", "Wrongpassword!@1"))
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(400)
                .body("statusCode", equalTo("400"))
                .body("msg", equalTo("회원을 찾을 수 없습니다."));
    }

    @Test
    @DisplayName("로그인 시, 데이터가 전달되지 않은 경우")
    void testLogin_MissingCredentials() {
        given().contentType(ContentType.JSON)
                .body(new LoginRequest("", ""))
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(400)
                .body("statusCode", equalTo("400"))
                .body("msg", equalTo("회원을 찾을 수 없습니다."));
    }
}
