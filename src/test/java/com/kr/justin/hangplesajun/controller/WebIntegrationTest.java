package com.kr.justin.hangplesajun.controller;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import com.kr.justin.hangplesajun.config.CustomUserDetailsService;
import com.kr.justin.hangplesajun.util.JwtUtil;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WebIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	String jwtToken;

	@Autowired
	private JwtUtil jwtUtil;



	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		RestAssuredMockMvc.mockMvc(mockMvc);

		// Create a mock user and generate a JWT token
		UserDetails mockUserDetails = org.springframework.security.core.userdetails.User.withUsername("user1")
			.password("password1")
			.roles("USER")
			.build();

		jwtToken = jwtUtil.generateToken(mockUserDetails.getUsername());

	}

	protected RequestSpecification givenAuth() {
		return given()
			.header("Authorization", "Bearer " + jwtToken);
	}


}
