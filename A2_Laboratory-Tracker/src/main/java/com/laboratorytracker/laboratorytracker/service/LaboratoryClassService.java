package com.laboratorytracker.laboratorytracker.service;


import com.laboratorytracker.laboratorytracker.model.LaboratoryClass;
import com.laboratorytracker.laboratorytracker.repository.LaboratoryClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryClassService {
    @Autowired
    private LaboratoryClassRepository laboratoryClassRepository;

    public void saveLaboratory(LaboratoryClass laboratoryClass){
        laboratoryClassRepository.save(laboratoryClass);
    }

    public List<LaboratoryClass> getAllLaboratories(){
        return laboratoryClassRepository.findAll();
    }

    public LaboratoryClass getLaboratory(Integer id){
        return laboratoryClassRepository.findById(id).get();
    }

    public void deleteLaboratory(Integer id){
        laboratoryClassRepository.deleteById(id);
    }
}
