package com.dinhquangduy.electronic.bean.response;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.dinhquangduy.electronic.bean.entity.RoleEntity;

public class AuthenTokenResponse {

    private String accessToken;

    private String tokenType;
    
    private String username;
    
    private Collection<? extends GrantedAuthority> roles;
    
    public AuthenTokenResponse() {
        super();
    }

    public AuthenTokenResponse(String accessToken, String username, Collection<? extends GrantedAuthority> role) {
        this.accessToken = accessToken;
        this.username = username;
        this.roles = role;
        this.tokenType = "Bearer";
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
