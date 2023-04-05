package com.laboratorytracker.laboratorytracker.repository;


import com.laboratorytracker.laboratorytracker.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
