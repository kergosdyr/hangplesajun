package com.kr.justin.hangplesajun.config;

import java.util.List;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class UserPrincipal extends User {

    private final long id;

    public UserPrincipal(com.kr.justin.hangplesajun.domain.User user) {
        super(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRoles())));
        this.id = user.getId();
    }
}
