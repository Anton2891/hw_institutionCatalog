package com.example.user_service;

import com.example.user_service.entity.Role;
import com.example.user_service.entity.User;
import com.example.user_service.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final User entityUser;

    public MyUserDetails(User entityUser) {
        this.entityUser = entityUser;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
//        for (Role role : entityUser.getPassword()){
//        list.add(new SimpleGrantedAuthority(role.getName()));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return null;
    }

    @Override
    public String getPassword() {
        return entityUser.getPassword();
    }

    @Override
    public String getUsername() {
        return entityUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO: add expiration date in DB
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //TODO: add lock flag in DB
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO: add password expirition date
        return true;
    }

    @Override
    public boolean isEnabled() {
        //TODO: add lock flag in DB
        return true;
    }
}
