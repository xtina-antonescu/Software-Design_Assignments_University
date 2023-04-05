package com.laboratorytracker.laboratorytracker.repository;


import com.laboratorytracker.laboratorytracker.model.LaboratoryClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryClassRepository extends JpaRepository<LaboratoryClass, Integer> {
}
