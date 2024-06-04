package com.app.service;

import com.app.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomEmployeeDetails implements UserDetails {

    private Employee authEmpDetails;

    public CustomEmployeeDetails(Employee authEmpDetails) {
        super();
        this.authEmpDetails = authEmpDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authEmpDetails.getRole().name()));
    }

    @Override
    public String getPassword() {
        return authEmpDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return authEmpDetails.getFirstName() + " " + authEmpDetails.getLastName();
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
