package com.kr.justin.hangplesajun.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank(message = "사용자 이름은 비워둘 수 없습니다.")
                @Size(min = 4, max = 10, message = "사용자 이름은 4자 이상, 10자 이하여야 합니다.")
                @Pattern(regexp = "^[a-z0-9]+$", message = "사용자 이름은 소문자 알파벳(a~z) 및 숫자(0~9)로 구성되어야 합니다.")
                String username,
        @NotBlank(message = "비밀번호는 비워둘 수 없습니다.")
                @Size(min = 8, max = 15, message = "비밀번호는 8자 이상, 15자 이하여야 합니다.")
                @Pattern(
                        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).+$",
                        message = "비밀번호는 대문자, 소문자, 숫자 및 특수 문자를 모두 포함해야 합니다.")
                String password) {}
