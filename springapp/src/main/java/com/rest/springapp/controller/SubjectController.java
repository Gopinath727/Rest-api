package com.rest.springapp.controller;

import com.rest.springapp.model.Subject;
import com.rest.springapp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public Page<Subject> getAllSubjects(Pageable pageable) {
        return subjectService.getSubjects(pageable);
    }

    @GetMapping("/search")
    public List<Subject> getSubjectsByName(@RequestParam String name) {
        return subjectService.getSubjectsByName(name);
    }

    @GetMapping("/code/{code}")
    public Optional<Subject> getSubjectByCode(@PathVariable String code) {
        return subjectService.getSubjectByCode(code);
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody Subject updatedSubject) {
        return subjectService.updateSubject(id, updatedSubject);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }
}
