package com.igor.approve_flow.model;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<ApproveRequest> approveRequests;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "assignees")
    private Set<ApproveRequest> approveRequestsAssigned;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created_at;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime last_update;


    public User() {
    }

    public User(Long id, String name, String email, String password, List<ApproveRequest> approveRequests, Set<ApproveRequest> approveRequestsAssigned, LocalDateTime created_at, LocalDateTime last_update) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.approveRequests = approveRequests;
        this.approveRequestsAssigned = approveRequestsAssigned;
        this.created_at = created_at;
        this.last_update = last_update;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ApproveRequest> getApproveRequests() {
        return approveRequests;
    }

    public void setApproveRequests(List<ApproveRequest> approveRequests) {
        this.approveRequests = approveRequests;
    }

    public Set<ApproveRequest> getApproveRequestsAssigned() {
        return approveRequestsAssigned;
    }

    public void setApproveRequestsAssigned(Set<ApproveRequest> approveRequestsAssigned) {
        this.approveRequestsAssigned = approveRequestsAssigned;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
