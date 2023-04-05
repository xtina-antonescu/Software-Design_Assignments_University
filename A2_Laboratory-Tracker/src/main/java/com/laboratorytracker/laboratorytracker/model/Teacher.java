package com.laboratorytracker.laboratorytracker.model;

import jakarta.persistence.*;

enum Position {
    TA,
    Professor,
    AssociateProfessor,
    AssistantProfessor
}

@Entity
public class Teacher extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherID;

    private int experience;

    @Enumerated(EnumType.STRING)
    private Position position;

    public Teacher(String email, String password, int experience, Position position) {
        super(email, password);
        this.experience = experience;
        this.position = position;
    }

    public Teacher(){
        super();
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
