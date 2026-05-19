package com.igor.approve_flow.repository;

import com.igor.approve_flow.model.ApproveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproveRequestRepository extends JpaRepository<ApproveRequest, Long> {
}
