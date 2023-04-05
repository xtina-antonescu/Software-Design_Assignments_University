package com.laboratorytracker.laboratorytracker.model;

import jakarta.persistence.*;

enum Group{
    GROUP_30431,
    GROUP_30432,
    GROUP_30433,
    GROUP_30434
}

@Entity
public class Student extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentID;

    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_group")
    private Group group;
    private String hobby;

    @Column(unique = true)
    private String token;

    public Student(String email, String password, String fullName, Group group, String hobby, String token) {
        super(email, password);
        this.fullName = fullName;
        this.group = group;
        this.hobby = hobby;
        this.token = token;
    }

    public Student(){
        super();
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
