package com.rest.springapp.controller;

import com.rest.springapp.model.Classroom;
import com.rest.springapp.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    public Page<Classroom> getAllClassrooms(Pageable pageable) {
        return classroomService.getClassrooms(pageable);
    }

    @GetMapping("/search")
    public List<Classroom> getClassroomsByName(@RequestParam String name) {
        return classroomService.getClassroomsByName(name);
    }

    @GetMapping("/capacity/{capacity}")
    public List<Classroom> getClassroomsByCapacity(@PathVariable int capacity) {
        return classroomService.getClassroomsByCapacity(capacity);
    }

    @PostMapping
    public Classroom createClassroom(@RequestBody Classroom classroom) {
        return classroomService.saveClassroom(classroom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @RequestBody Classroom updatedClassroom) {
        Classroom classroom = classroomService.updateClassroom(id, updatedClassroom);
        return ResponseEntity.ok(classroom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok("Classroom deleted successfully");
    }
}
