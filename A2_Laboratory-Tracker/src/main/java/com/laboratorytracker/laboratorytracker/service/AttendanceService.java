package com.laboratorytracker.laboratorytracker.service;


import com.laboratorytracker.laboratorytracker.model.Attendance;
import com.laboratorytracker.laboratorytracker.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public void saveAttendace(Attendance attendance){
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendances(){
        return attendanceRepository.findAll();
    }

    public Attendance getAttendance(Integer id){
        return attendanceRepository.findById(id).get();
    }

    public void deleteAttendance(Integer id){
        attendanceRepository.deleteById(id);
    }
}
