package com.kr.justin.hangplesajun.controller;

import static io.restassured.RestAssured.given;

import com.kr.justin.hangplesajun.util.JwtUtil;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WebIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    String jwtToken;

    String adminJwtToken;

    @Autowired
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssuredMockMvc.mockMvc(mockMvc);

        // Create a mock user and generate a JWT token
        UserDetails mockUser = org.springframework.security.core.userdetails.User.withUsername("user1")
                .password("password1")
                .roles("USER")
                .build();

        // Create a mock user and generate a JWT token
        UserDetails mockAdminUser = org.springframework.security.core.userdetails.User.withUsername("user1")
                .password("password1")
                .roles("ADMIN")
                .build();

        jwtToken = jwtUtil.generateToken(mockUser.getUsername());
        adminJwtToken = jwtUtil.generateToken(mockAdminUser.getUsername());
    }

    protected RequestSpecification givenAuth() {
        return given().header("Authorization", "Bearer " + jwtToken);
    }

    protected RequestSpecification givenAdminAuth() {
        return given().header("Authorization", "Bearer " + adminJwtToken);
    }
}
