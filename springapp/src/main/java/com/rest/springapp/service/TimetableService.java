package com.rest.springapp.service;

import com.rest.springapp.model.Timetable;
import com.rest.springapp.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    public Page<Timetable> getTimetables(Pageable pageable) {
        return timetableRepository.findAllSortedByStartTime(pageable);
    }

    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    public List<Timetable> getTimetablesBySubject(String subject) {
        return timetableRepository.findBySubject(subject);
    }

    public List<Timetable> getTimetablesBetweenTimes(LocalDateTime startTime, LocalDateTime endTime) {
        return timetableRepository.findTimetablesBetweenTimes(startTime, endTime);
    }

    public Timetable saveTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    public Timetable updateTimetable(Long id, Timetable updatedTimetable) {
        return timetableRepository.findById(id).map(timetable -> {
            timetable.setSubject(updatedTimetable.getSubject());
            timetable.setStartTime(updatedTimetable.getStartTime());
            timetable.setEndTime(updatedTimetable.getEndTime());
            return timetableRepository.save(timetable);
        }).orElseThrow(() -> new RuntimeException("Timetable not found with id " + id));
    }

    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }
}
