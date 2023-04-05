package com.laboratorytracker.laboratorytracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="studentID",  nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "laboratoryNumber",  nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LaboratoryClass laboratoryClass;

    private Boolean present;

    public Attendance() {
    }

    public Attendance(Student student, LaboratoryClass laboratoryClass, Boolean present) {
        this.student = student;
        this.laboratoryClass = laboratoryClass;
        this.present = present;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LaboratoryClass getLaboratoryClass() {
        return laboratoryClass;
    }

    public void setLaboratoryClass(LaboratoryClass laboratoryClass) {
        this.laboratoryClass = laboratoryClass;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
