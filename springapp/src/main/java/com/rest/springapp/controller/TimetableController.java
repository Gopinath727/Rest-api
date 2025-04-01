package com.rest.springapp.controller;

import com.rest.springapp.model.Timetable;
import com.rest.springapp.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/timetables")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping
    public Page<Timetable> getAllTimetables(Pageable pageable) {
        return timetableService.getTimetables(pageable);
    }

    @GetMapping("/search")
    public List<Timetable> getTimetablesBySubject(@RequestParam String subject) {
        return timetableService.getTimetablesBySubject(subject);
    }

    @GetMapping("/between")
    public List<Timetable> getTimetablesBetweenTimes(@RequestParam LocalDateTime startTime, 
                                                     @RequestParam LocalDateTime endTime) {
        return timetableService.getTimetablesBetweenTimes(startTime, endTime);
    }

    @PostMapping
    public Timetable createTimetable(@RequestBody Timetable timetable) {
        return timetableService.saveTimetable(timetable);
    }

    @PutMapping("/{id}")
    public Timetable updateTimetable(@PathVariable Long id, @RequestBody Timetable updatedTimetable) {
        return timetableService.updateTimetable(id, updatedTimetable);
    }

    @DeleteMapping("/{id}")
    public void deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
    }
}
