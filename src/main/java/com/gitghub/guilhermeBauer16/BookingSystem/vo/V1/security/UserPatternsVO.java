package com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security;

import com.gitghub.guilhermeBauer16.BookingSystem.model.security.PermissionModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class UserPatternsVO implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;

    private String username;

    private String password;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private PermissionModel permission;

    private Boolean active;

    public UserPatternsVO() {
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        roles.add(permission.getDescription());
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(permission.getDescription()));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public PermissionModel getPermission() {
        return permission;
    }

    public void setPermission(PermissionModel permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPatternsVO vo = (UserPatternsVO) o;
        return Objects.equals(id, vo.id) && Objects.equals(username, vo.username) && Objects.equals(password, vo.password) && Objects.equals(accountNonExpired, vo.accountNonExpired) && Objects.equals(accountNonLocked, vo.accountNonLocked) && Objects.equals(credentialsNonExpired, vo.credentialsNonExpired) && Objects.equals(enabled, vo.enabled) && Objects.equals(permission, vo.permission) && Objects.equals(active, vo.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, permission, active);
    }
}
