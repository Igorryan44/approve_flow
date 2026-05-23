package com.igor.approve_flow.repository;

import com.igor.approve_flow.model.ApproveRequest;
import com.igor.approve_flow.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApproveRequestRepository extends JpaRepository<ApproveRequest, Long> {

    Optional<List<ApproveRequest>> listByStatus(RequestStatus status);
}
