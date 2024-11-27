package com.kr.justin.hangplesajun.config;

import java.util.List;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class UserPrincipal extends User {

    @Getter
    private final long id;

    private final String roles;

    public UserPrincipal(com.kr.justin.hangplesajun.domain.User user) {
        super(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRoles())));
        this.id = user.getId();
        this.roles = user.getRoles();
    }

    public boolean isAdmin() {
        return this.roles.contains("ROLE_ADMIN");
    }
}
