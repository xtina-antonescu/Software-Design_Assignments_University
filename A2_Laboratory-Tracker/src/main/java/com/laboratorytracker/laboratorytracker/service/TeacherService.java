package com.laboratorytracker.laboratorytracker.service;


import com.laboratorytracker.laboratorytracker.model.Teacher;
import com.laboratorytracker.laboratorytracker.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public void save(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(Integer id){
        return teacherRepository.findById(id).get();
    }

    public void delete(Integer id){
        teacherRepository.deleteById(id);
    }

    public Teacher authenticate(String email, String password){
        for(Teacher teacher: teacherRepository.findAll()){
            String encrypt = Base64.getEncoder().encodeToString(password.getBytes());
            if(teacher.getEmail().equals(email) && teacher.getPassword().equals(encrypt)){
                return teacher;
            }
        }
        return null;
    }
}
