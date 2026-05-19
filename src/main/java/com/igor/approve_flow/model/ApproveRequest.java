package com.igor.approve_flow.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "approve_request")
public class ApproveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;
    @Enumerated(value = EnumType.STRING)
    private RequestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime las_update;

    public ApproveRequest() {
    }

    public ApproveRequest(Long id, User user_id, RequestStatus status, LocalDateTime createdAt, LocalDateTime las_update) {
        this.id = id;
        this.user_id = user_id;
        this.status = status;
        this.createdAt = createdAt;
        this.las_update = las_update;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
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
        return las_update;
    }

    public void setLas_update(LocalDateTime las_update) {
        this.las_update = las_update;
    }
}
