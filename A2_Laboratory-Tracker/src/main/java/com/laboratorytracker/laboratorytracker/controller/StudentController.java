package com.laboratorytracker.laboratorytracker.controller;

import com.laboratorytracker.laboratorytracker.model.*;
import com.laboratorytracker.laboratorytracker.service.AssignmentService;
import com.laboratorytracker.laboratorytracker.service.AssignmentSubmissionService;
import com.laboratorytracker.laboratorytracker.service.LaboratoryClassService;
import com.laboratorytracker.laboratorytracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LaboratoryClassService laboratoryClassService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentSubmissionService assignmentSubmissionService;

    @GetMapping("/log-in/{email}/{password}")
    public ResponseEntity<Student> logIn(@PathVariable String email, @PathVariable String password){
        try{
            Student student = studentService.authenticate(email, password);
            if(student != null){
                student.setAuthenticated(true);
                studentService.save(student);
                return new ResponseEntity<>(student, HttpStatus.OK);
            }

        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/log-off/{email}/{password}")
    public ResponseEntity<Student> logOff(@PathVariable String email, @PathVariable String password) {
        try{
            Student student = studentService.authenticate(email, password);
            if(student != null && student.getAuthenticated()){
                student.setAuthenticated(false);
                studentService.save(student);
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/register/{email}/{token}")
    public ResponseEntity<Student> register(@PathVariable String email, @PathVariable String token, @RequestBody Student student){
        Student studentFound = studentService.registerStudent(token,email);
        try{
            if(studentFound != null){
                int id = studentFound.getStudentID();
                student.setToken(studentFound.getToken());
                student.setEmail(studentFound.getEmail());
                String encrypt = Base64.getEncoder().encodeToString(student.getPassword().getBytes());
                student.setPassword(encrypt);
                student.setStudentID(id);
                studentService.save(student);
                return new ResponseEntity<>(student, HttpStatus.OK);
            }

        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/{email}/{password}/view-laboratories")
    public ResponseEntity<List<LaboratoryClass>> viewLaboratories(@PathVariable String email, @PathVariable String password){
        Student student = studentService.authenticate(email, password);
        try{
            if(student.getAuthenticated()) {
                List<LaboratoryClass> laboratoryClasses = laboratoryClassService.getAllLaboratories();
                return new ResponseEntity<>(laboratoryClasses, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{email}/{password}/view-assignments/{id}")
    public ResponseEntity<List<Assignment>> viewAssignments(@PathVariable Integer id, @PathVariable String email, @PathVariable String password){
        Student student = studentService.authenticate(email, password);
        try{
            if(student.getAuthenticated()) {
                List<Assignment> assignmentList = assignmentService.getAssignmentsByLaboratory(id);
                return new ResponseEntity<>(assignmentList, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{email}/{password}/add-submission/{id}")
    public ResponseEntity<AssignmentSubmission> addSubmission(@RequestBody AssignmentSubmission assignmentSubmission, @PathVariable String email, @PathVariable String password, @PathVariable Integer id){
        Student student = studentService.authenticate(email, password);
        try{
            if(student.getAuthenticated()) {
                assignmentSubmission.setStudent(student);
                assignmentSubmission.setAssignment(assignmentService.getAssignment(id));
                assignmentSubmissionService.saveSubmission(assignmentSubmission);
                return new ResponseEntity<>(assignmentSubmission, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
