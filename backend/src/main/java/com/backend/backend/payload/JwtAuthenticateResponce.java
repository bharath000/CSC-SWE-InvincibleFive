package com.backend.backend.payload;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticateResponce {
    private Long Id;
    private String username;
    private String email;
    private String accessToken;
    private String tokenType = "Bearer";
    private  Collection<? extends GrantedAuthority> role;
    private Long zipcode;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(Collection<? extends GrantedAuthority> role) {
        this.role = role;
    }

    public JwtAuthenticateResponce(String accessToken, Long Id, String username, String email, Collection<? extends GrantedAuthority> role, Long zipcode) {
        this.accessToken = accessToken;
        this.Id = Id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.zipcode = zipcode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getZipcode() {
        return zipcode;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
