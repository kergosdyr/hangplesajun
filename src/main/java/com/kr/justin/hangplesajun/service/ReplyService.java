package com.kr.justin.hangplesajun.service;

import com.kr.justin.hangplesajun.domain.Post;
import com.kr.justin.hangplesajun.domain.Reply;
import com.kr.justin.hangplesajun.repository.PostRepository;
import com.kr.justin.hangplesajun.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    @Transactional
    public Reply create(long postId, long userId, String content) {
        Post post =
                postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다."));
        return replyRepository.save(Reply.of(postId, userId, content));
    }

    @Transactional
    public Reply update(long id, long userId, String content) {
        Reply reply = replyRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않거나 권한이 없습니다."));
        reply.updateContent(content);
        return reply;
    }

    @Transactional
    public void delete(long id, long userId) {
        Reply reply = replyRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않거나 권한이 없습니다."));
        replyRepository.delete(reply);
    }
}
