package com.rest.springapp.service;

import com.rest.springapp.model.ExamSchedule;
import com.rest.springapp.repository.ExamScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExamScheduleService {

    @Autowired
    private ExamScheduleRepository examScheduleRepository;

    public Page<ExamSchedule> getExamSchedules(Pageable pageable) {
        return examScheduleRepository.findAllSortedByExamDate(pageable);
    }

    public List<ExamSchedule> getAllExamSchedules() {
        return examScheduleRepository.findAll();
    }

    public List<ExamSchedule> getExamSchedulesBySubject(String subject) {
        return examScheduleRepository.findBySubject(subject);
    }

    public List<ExamSchedule> getExamSchedulesBetweenDates(LocalDateTime startTime, LocalDateTime endTime) {
        return examScheduleRepository.findBetweenDates(startTime, endTime);
    }

    public ExamSchedule saveExamSchedule(ExamSchedule examSchedule) {
        return examScheduleRepository.save(examSchedule);
    }

    public Optional<ExamSchedule> getExamScheduleById(Long id) {
        return examScheduleRepository.findById(id);
    }

    public ExamSchedule updateExamSchedule(Long id, ExamSchedule updatedExamSchedule) {
        return examScheduleRepository.findById(id).map(examSchedule -> {
            examSchedule.setSubject(updatedExamSchedule.getSubject());
            examSchedule.setExamDate(updatedExamSchedule.getExamDate());
            examSchedule.setClassroom(updatedExamSchedule.getClassroom());
            examSchedule.setInvigilator(updatedExamSchedule.getInvigilator());
            return examScheduleRepository.save(examSchedule);
        }).orElse(null);
    }

    public void deleteExamSchedule(Long id) {
        examScheduleRepository.deleteById(id);
    }
}
