package com.kr.justin.hangplesajun.controller;

import com.kr.justin.hangplesajun.config.UserPrincipal;
import com.kr.justin.hangplesajun.domain.Reply;
import com.kr.justin.hangplesajun.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/api/posts/{postId}/replies")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReplyResponse> createReply(
            @PathVariable long postId, @RequestBody ReplyRequest request, @AuthenticationPrincipal UserPrincipal user) {
        Reply reply = replyService.create(postId, user.getId(), request.content());
        return ResponseEntity.ok(ReplyResponse.from(reply));
    }

    @PutMapping("/api/replies/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReplyResponse> updateReply(
            @PathVariable long id, @RequestBody ReplyRequest request, @AuthenticationPrincipal UserPrincipal user) {
        Reply updatedReply = replyService.update(id, user.getId(), request.content());
        return ResponseEntity.ok(ReplyResponse.from(updatedReply));
    }

    @DeleteMapping("/api/replies/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReplyDeleteResponse> deleteReply(
            @PathVariable long id, @AuthenticationPrincipal UserPrincipal user) {
        replyService.delete(id, user.getId());
        return ResponseEntity.ok(ReplyDeleteResponse.success());
    }
}
