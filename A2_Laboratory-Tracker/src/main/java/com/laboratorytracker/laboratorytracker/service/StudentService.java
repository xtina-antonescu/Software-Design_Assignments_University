package com.laboratorytracker.laboratorytracker.service;


import com.laboratorytracker.laboratorytracker.model.Student;
import com.laboratorytracker.laboratorytracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void save(Student student){
        studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Integer id){
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);
    }

    public Student authenticate(String email, String password){
        for(Student student: studentRepository.findAll()){
            String encrypt = Base64.getEncoder().encodeToString(password.getBytes());
            if(student.getEmail().equals(email) && student.getPassword().equals(encrypt)){
                return student;
            }
        }
        return null;
    }

    public Student registerStudent(String token, String email){
        for(Student student:studentRepository.findAll()){
            if(student.getToken().equals(token) && student.getEmail().equals(email)){
                return student;
            }
        }
        return null;
    }
}
