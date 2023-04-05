package com.laboratorytracker.laboratorytracker.repository;


import com.laboratorytracker.laboratorytracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
