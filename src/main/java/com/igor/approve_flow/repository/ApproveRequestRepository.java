package com.igor.approve_flow.repository;

import com.igor.approve_flow.model.ApproveRequest;
import com.igor.approve_flow.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApproveRequestRepository extends JpaRepository<ApproveRequest, Long> {

    List<ApproveRequest> findByStatus(RequestStatus status);
    @Query("SELECT a FROM ApproveRequest a WHERE a.status = 'IN_PROGRESS'")
    List<ApproveRequest> findByStatusInProgress();
}
