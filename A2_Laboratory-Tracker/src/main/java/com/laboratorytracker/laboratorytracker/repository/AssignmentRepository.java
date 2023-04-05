package com.laboratorytracker.laboratorytracker.repository;


import com.laboratorytracker.laboratorytracker.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
