package com.app.service;

import com.app.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class CustomUserDetails implements UserDetails {
    private Employee authUserDetails;

    public CustomUserDetails(Employee authenticatedUser) {
        super();
        this.authUserDetails = authenticatedUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<SimpleGrantedAuthority> set = new HashSet<>();
        set.add(new SimpleGrantedAuthority(authUserDetails.getUserRole().name()));
        System.out.println(set.toString());
        return set;
    }

    @Override
    public String getPassword() {
        return authUserDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return authUserDetails.getEmail();
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
}
