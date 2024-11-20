package com.kr.justin.hangplesajun.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kr.justin.hangplesajun.controller.PostSearchQuery;
import com.kr.justin.hangplesajun.domain.Post;
import com.kr.justin.hangplesajun.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;


	@Transactional(readOnly = true)
	public Post get(Long id) {

		return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다"));

	}

	@Transactional(readOnly = true)
	public List<Post> getAllBy(PostSearchQuery query) {

		return postRepository.getAllBy(query);

	}

	@Transactional
	public Post create(Post post) {

		return postRepository.save(post);

	}

	@Transactional
	public Post update(Long id, Post post) {
		Post existPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다"));
		existPost.update(post);
		return existPost;
	}

	@Transactional
	public void delete(Long id) {
		postRepository.deleteById(id);
	}
}
