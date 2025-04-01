package com.rest.springapp.service;

import com.rest.springapp.model.Classroom;
import com.rest.springapp.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public Page<Classroom> getClassrooms(Pageable pageable) {
        return classroomRepository.findAllSortedByName(pageable);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public List<Classroom> getClassroomsByName(String name) {
        return classroomRepository.findByName(name);
    }

    public List<Classroom> getClassroomsByCapacity(int capacity) {
        return classroomRepository.findByMinimumCapacity(capacity);
    }

    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Classroom updateClassroom(Long id, Classroom updatedClassroom) {
        return classroomRepository.findById(id).map(classroom -> {
            classroom.setName(updatedClassroom.getName());
            classroom.setCapacity(updatedClassroom.getCapacity());
            return classroomRepository.save(classroom);
        }).orElseThrow(() -> new RuntimeException("Classroom not found with id: " + id));
    }

    public void deleteClassroom(Long id) {
        if (!classroomRepository.existsById(id)) {
            throw new RuntimeException("Classroom not found with id: " + id);
        }
        classroomRepository.deleteById(id);
    }
}
