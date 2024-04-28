package com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class PermissionVO implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String description;

    private Boolean active;

    public PermissionVO() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String getAuthority() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionVO that = (PermissionVO) o;
        return Objects.equals(description, that.description) && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, active);
    }
}
