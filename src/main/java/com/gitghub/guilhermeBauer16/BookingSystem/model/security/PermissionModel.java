package com.gitghub.guilhermeBauer16.BookingSystem.model.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "permission")
public class PermissionModel implements GrantedAuthority, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String description;


    private boolean active;




    public PermissionModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
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
        PermissionModel that = (PermissionModel) o;
        return active == that.active && Objects.equals(description, that.description);
    }

    // Updated hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(description, active);
    }
}
