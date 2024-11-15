package com.example.healthcare_back.common.object;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;

// 사용자 지정 OAuth2 유저 객체
@Getter
public class CustomOAuth2User implements OAuth2User {

    private final String name;
    private final Map<String, Object> attributes;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean existed;

    public CustomOAuth2User(String userId, Map<String, Object> attributes, boolean existed) {
        this.name = userId;
        this.attributes = attributes;
        this.authorities = AuthorityUtils.NO_AUTHORITIES;
        this.existed = existed;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean isExisted() {
        return this.existed;
    }
    
}
