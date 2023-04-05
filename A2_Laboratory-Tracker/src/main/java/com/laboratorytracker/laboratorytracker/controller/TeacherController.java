package com.laboratorytracker.laboratorytracker.controller;


import com.laboratorytracker.laboratorytracker.model.*;
import com.laboratorytracker.laboratorytracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LaboratoryClassService laboratoryClassService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentSubmissionService assignmentSubmissionService;

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        try{
            teacherService.save(teacher);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/log-in/{email}/{password}")
    public ResponseEntity<Teacher> logIn(@PathVariable String email, @PathVariable String password){
        try{
            Teacher teacher = teacherService.authenticate(email, password);
            if(teacher != null){
                teacher.setAuthenticated(true);
                teacherService.save(teacher);
                return new ResponseEntity<>(teacher, HttpStatus.OK);
            }

        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/log-off/{email}/{password}")
    public ResponseEntity<Teacher> logOff(@PathVariable String email, @PathVariable String password) {
        try{
            Teacher teacher = teacherService.authenticate(email, password);
            if(teacher != null && teacher.getAuthenticated()){
                teacher.setAuthenticated(false);
                teacherService.save(teacher);
                return new ResponseEntity<>(teacher, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{email}/{password}/add-student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        try{
            if(teacher.getAuthenticated()) {
                studentService.save(student);
                return new ResponseEntity<>(student,HttpStatus.OK);
            }

        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{email}/{password}/edit-student/{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable Integer id, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        try{
            if(teacher.getAuthenticated()) {
                student.setStudentID(id);
                studentService.save(student);
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{email}/{password}/delete-student/{id}")
    public String deleteStudent(@PathVariable Integer id, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        if(teacher.getAuthenticated()) {
            studentService.deleteStudent(id);
            return "Student deleted successfully!";
        }
        return "Could not delete student";
    }

    @PostMapping("/{email}/{password}/add-laboratory")
    public ResponseEntity<LaboratoryClass> addLaboratory(@RequestBody LaboratoryClass laboratoryClass, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        try{
            if(teacher.getAuthenticated()) {
                laboratoryClassService.saveLaboratory(laboratoryClass);
                return new ResponseEntity<>(laboratoryClass, HttpStatus.OK);
            }

        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{email}/{password}/edit-laboratory/{id}")
    public ResponseEntity<LaboratoryClass> editLaboratory(@RequestBody LaboratoryClass laboratoryClass, @PathVariable Integer id,  @PathVariable String email, @PathVariable String password) {
        Teacher teacher = teacherService.authenticate(email, password);
        try {
            if(teacher.getAuthenticated()) {
                laboratoryClass.setLaboratoryNumber(id);
                laboratoryClassService.saveLaboratory(laboratoryClass);
                return new ResponseEntity<>(laboratoryClass, HttpStatus.OK);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{email}/{password}/delete-laboratory/{id}")
    public String deleteLaboratory(@PathVariable Integer id, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        if(teacher.getAuthenticated()) {
            laboratoryClassService.deleteLaboratory(id);
            return "Laboratory deleted successfully!";
        }
        return "Could not delete laboratory!";
    }

    @PostMapping("/{email}/{password}/add-attendance/{labID}/{studID}")
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance, @PathVariable String email, @PathVariable String password, @PathVariable Integer labID, @PathVariable Integer studID){
        Teacher teacher = teacherService.authenticate(email, password);
        try{
            if(teacher.getAuthenticated()) {
                attendance.setLaboratoryClass(laboratoryClassService.getLaboratory(labID));
                attendance.setStudent(studentService.getStudent(studID));
                attendanceService.saveAttendace(attendance);
                return new ResponseEntity<>(attendance, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{email}/{password}/edit-attendance/{id}")
    public ResponseEntity<Attendance> editAttendance(@RequestBody Attendance attendance, @PathVariable Integer id, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        try{
            if(teacher.getAuthenticated()) {
                attendance.setId(id);
                attendanceService.saveAttendace(attendance);
                return new ResponseEntity<>(attendance, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{email}/{password}/delete-attendance/{id}")
    public String deleteAttendance(@PathVariable Integer id, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        if(teacher.getAuthenticated()) {
            attendanceService.deleteAttendance(id);
            return "Attendance deleted successfully!";
        }
        return "Could not delete attendance!";
    }

    @PostMapping ("/{email}/{password}/add-assignment/{id}")
    public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment, @PathVariable String email, @PathVariable String password, @PathVariable Integer id){
        Teacher teacher = teacherService.authenticate(email, password);
        try{
            if(teacher.getAuthenticated()) {
                LaboratoryClass laboratoryClass = laboratoryClassService.getLaboratory(id);
                assignment.setLaboratoryClass(laboratoryClass);
                assignmentService.saveAssignment(assignment);
                return new ResponseEntity<>(assignment, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{email}/{password}/edit-assignment/{id}")
    public ResponseEntity<Assignment> editAssignment(@RequestBody Assignment assignment, @PathVariable Integer id, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        try{
            if(teacher.getAuthenticated()) {
                assignment.setAssignmentNumber(id);
                assignmentService.saveAssignment(assignment);
                return new ResponseEntity<>(assignment, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{email}/{password}/delete-assignment/{id}")
    public String deleteAssignment(@PathVariable Integer id, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        if(teacher.getAuthenticated()) {
            assignmentService.deleteAssignment(id);
            return "Assignment delete successfully!";
        }
        return "Could not delete assignment!";
    }

    @PutMapping("/{email}/{password}/grade-assignment/{id}/{grade}")
    public ResponseEntity<AssignmentSubmission> gradeAssignment(@PathVariable Integer id, @PathVariable Integer grade, @PathVariable String email, @PathVariable String password){
        Teacher teacher = teacherService.authenticate(email, password);
        try{
            if(teacher.getAuthenticated()) {
                AssignmentSubmission assignmentSubmission = assignmentSubmissionService.getSubmission(id);
                assignmentSubmission.setGrade(grade);
                assignmentSubmissionService.saveSubmission(assignmentSubmission);
                return new ResponseEntity<>(assignmentSubmission, HttpStatus.OK);
            }
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
