package com.kr.justin.hangplesajun.controller;

import jakarta.validation.constraints.NotBlank;

public record ReplyRequest(@NotBlank String content) {}
