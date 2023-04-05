package com.laboratorytracker.laboratorytracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentNumber;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "laboratoryNumber",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LaboratoryClass laboratoryClass;

    public Assignment() {
    }

    public Assignment(int assignmentNumber, String name, Date deadline, String description) {
        this.assignmentNumber = assignmentNumber;
        this.name = name;
        this.deadline = deadline;
        this.description = description;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LaboratoryClass getLaboratoryClass() {
        return laboratoryClass;
    }

    public void setLaboratoryClass(LaboratoryClass laboratoryClass) {
        this.laboratoryClass = laboratoryClass;
    }
}
