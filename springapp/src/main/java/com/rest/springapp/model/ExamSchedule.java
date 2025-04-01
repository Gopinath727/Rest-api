package com.rest.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exam_schedule")
public class ExamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private LocalDateTime examDate;
    private String classroom;
    private String invigilator;

    public ExamSchedule() {}

    public ExamSchedule(String subject, LocalDateTime examDate, String classroom, String invigilator) {
        this.subject = subject;
        this.examDate = examDate;
        this.classroom = classroom;
        this.invigilator = invigilator;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public LocalDateTime getExamDate() { return examDate; }
    public void setExamDate(LocalDateTime examDate) { this.examDate = examDate; }

    public String getClassroom() { return classroom; }
    public void setClassroom(String classroom) { this.classroom = classroom; }

    public String getInvigilator() { return invigilator; }
    public void setInvigilator(String invigilator) { this.invigilator = invigilator; }
}
