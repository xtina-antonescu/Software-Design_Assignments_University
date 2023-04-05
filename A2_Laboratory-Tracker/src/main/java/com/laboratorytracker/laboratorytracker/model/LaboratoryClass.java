package com.laboratorytracker.laboratorytracker.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class LaboratoryClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int laboratoryNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date laboratoryDate;

    private String laboratoryTitle;
    private String curricula;
    private String description;

    public LaboratoryClass(int laboratoryNumber, Date laboratoryDate, String laboratoryTitle, String curricula, String description) {
        this.laboratoryNumber = laboratoryNumber;
        this.laboratoryDate = laboratoryDate;
        this.laboratoryTitle = laboratoryTitle;
        this.curricula = curricula;
        this.description = description;
    }

    public LaboratoryClass() {
    }

    public int getLaboratoryNumber() {
        return laboratoryNumber;
    }

    public void setLaboratoryNumber(int laboratoryNumber) {
        this.laboratoryNumber = laboratoryNumber;
    }

    public Date getLaboratoryDate() {
        return laboratoryDate;
    }

    public void setLaboratoryDate(Date laboratoryDate) {
        this.laboratoryDate = laboratoryDate;
    }

    public String getLaboratoryTitle() {
        return laboratoryTitle;
    }

    public void setLaboratoryTitle(String laboratoryTitle) {
        this.laboratoryTitle = laboratoryTitle;
    }

    public String getCurricula() {
        return curricula;
    }

    public void setCurricula(String curricula) {
        this.curricula = curricula;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
