package com.store.security.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> authorities) {
        this.jwt = jwt;
        this.authorities = authorities;
    }

    public String getJwt() {
        return jwt;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(authorities);
    }
}
