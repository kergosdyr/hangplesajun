package com.kr.justin.hangplesajun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kr.justin.hangplesajun.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);


}
