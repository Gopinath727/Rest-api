package com.rest.springapp.controller;

import com.rest.springapp.model.ExamSchedule;
import com.rest.springapp.service.ExamScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam-schedules")
public class ExamScheduleController {

    @Autowired
    private ExamScheduleService examScheduleService;

    @GetMapping
    public Page<ExamSchedule> getAllExamSchedules(Pageable pageable) {
        return examScheduleService.getExamSchedules(pageable);
    }

    @GetMapping("/{id}")
    public Optional<ExamSchedule> getExamScheduleById(@PathVariable Long id) {
        return examScheduleService.getExamScheduleById(id);
    }

    @GetMapping("/search")
    public List<ExamSchedule> getExamSchedulesBySubject(@RequestParam String subject) {
        return examScheduleService.getExamSchedulesBySubject(subject);
    }

    @GetMapping("/between")
    public List<ExamSchedule> getExamSchedulesBetweenDates(@RequestParam LocalDateTime startTime, 
                                                           @RequestParam LocalDateTime endTime) {
        return examScheduleService.getExamSchedulesBetweenDates(startTime, endTime);
    }

    @PostMapping
    public ExamSchedule createExamSchedule(@RequestBody ExamSchedule examSchedule) {
        return examScheduleService.saveExamSchedule(examSchedule);
    }

    @PutMapping("/{id}")
    public ExamSchedule updateExamSchedule(@PathVariable Long id, @RequestBody ExamSchedule examSchedule) {
        return examScheduleService.updateExamSchedule(id, examSchedule);
    }

    @DeleteMapping("/{id}")
    public void deleteExamSchedule(@PathVariable Long id) {
        examScheduleService.deleteExamSchedule(id);
    }
}
