package com.laboratorytracker.laboratorytracker.service;


import com.laboratorytracker.laboratorytracker.model.AssignmentSubmission;
import com.laboratorytracker.laboratorytracker.repository.AssignmentSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentSubmissionService {
    @Autowired
    private AssignmentSubmissionRepository assignmentSubmissionRepository;

    public void saveSubmission(AssignmentSubmission assignmentSubmission){
        assignmentSubmissionRepository.save(assignmentSubmission);
    }

    public List<AssignmentSubmission> getAllSubmissions(){
        return assignmentSubmissionRepository.findAll();
    }

    public AssignmentSubmission getSubmission(Integer id){
        return assignmentSubmissionRepository.findById(id).get();
    }

    public void deleteSubmission(Integer id){
        assignmentSubmissionRepository.deleteById(id);
    }
}
