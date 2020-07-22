package com.backend.backend.security;

import com.backend.backend.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsPrincipals implements UserDetails {
    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private String username;
    private Long id;
    private Long zipcode;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsPrincipals(String email, String password, String username, Long id,  Collection<? extends GrantedAuthority> authorities, Long zipcode) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.id = id;
        this.zipcode = zipcode;
        this.authorities = authorities;
    }
    public static UserDetailsPrincipals create(Users user){
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getUser_role()));
        return new UserDetailsPrincipals(
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),

                user.getId(),
                authorities,
                user.getZipcode()

        );
    }
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public String getUser_role(){
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsPrincipals that = (UserDetailsPrincipals) o;
        return Objects.equals(id, that.id);
    }

    public Long getZipcode() {
        return zipcode;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
