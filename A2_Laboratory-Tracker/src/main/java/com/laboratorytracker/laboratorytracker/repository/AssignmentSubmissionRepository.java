package com.laboratorytracker.laboratorytracker.repository;


import com.laboratorytracker.laboratorytracker.model.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Integer> {
}
