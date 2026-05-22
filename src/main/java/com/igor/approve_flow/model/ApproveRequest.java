package com.igor.approve_flow.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "approve_request")
public class ApproveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(value = EnumType.STRING)
    private RequestStatus status;
    private LocalDateTime createdAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime last_update;

    public ApproveRequest() {
    }

    public ApproveRequest(Long id, User user, RequestStatus status, LocalDateTime createdAt, LocalDateTime last_update) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.createdAt = createdAt;
        this.last_update = last_update;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLas_update() {
        return last_update;
    }

    public void setLas_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }
}
