package com.rest.springapp.service;

import com.rest.springapp.model.Subject;
import com.rest.springapp.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Page<Subject> getSubjects(Pageable pageable) {
        return subjectRepository.findAllSortedByName(pageable);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectsByName(String name) {
        return subjectRepository.findByName(name);
    }

    public Optional<Subject> getSubjectByCode(String code) {
        return subjectRepository.findByCode(code);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long id, Subject updatedSubject) {
        return subjectRepository.findById(id).map(subject -> {
            subject.setName(updatedSubject.getName());
            subject.setCode(updatedSubject.getCode());
            return subjectRepository.save(subject);
        }).orElseThrow(() -> new RuntimeException("Subject not found with id " + id));
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
