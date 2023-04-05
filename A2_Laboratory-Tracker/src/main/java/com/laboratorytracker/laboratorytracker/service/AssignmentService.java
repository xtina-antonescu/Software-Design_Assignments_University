package com.laboratorytracker.laboratorytracker.service;


import com.laboratorytracker.laboratorytracker.model.Assignment;
import com.laboratorytracker.laboratorytracker.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public void saveAssignment(Assignment assignment){
        assignmentRepository.save(assignment);
    }

    public List<Assignment> geAllAssignments(){
        return assignmentRepository.findAll();
    }

    public Assignment getAssignment(Integer id){
        return assignmentRepository.findById(id).get();
    }

    public void deleteAssignment(Integer id){
        assignmentRepository.deleteById(id);
    }

    public List<Assignment> getAssignmentsByLaboratory(Integer id){
        List<Assignment> assignmentList = new ArrayList<>();
        for(Assignment assignment: assignmentRepository.findAll()){
            if(assignment.getLaboratoryClass().getLaboratoryNumber() == id){
                assignmentList.add(assignment);
            }
        }
        return assignmentList;
    }
}
