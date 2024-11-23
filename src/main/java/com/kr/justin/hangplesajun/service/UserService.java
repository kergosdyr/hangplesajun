package com.kr.justin.hangplesajun.service;

import com.kr.justin.hangplesajun.domain.User;
import com.kr.justin.hangplesajun.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(User user) {

        userRepository.save(user);
    }
}
