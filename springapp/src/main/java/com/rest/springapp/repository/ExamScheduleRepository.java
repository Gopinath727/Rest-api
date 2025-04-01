package com.rest.springapp.repository;

import com.rest.springapp.model.ExamSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Long> {

    @Query("SELECT e FROM ExamSchedule e WHERE LOWER(e.subject) LIKE LOWER(CONCAT('%', :subject, '%'))")
    List<ExamSchedule> findBySubject(@Param("subject") String subject);

    @Query("SELECT e FROM ExamSchedule e WHERE e.examDate BETWEEN :startTime AND :endTime ORDER BY e.examDate ASC")
    List<ExamSchedule> findBetweenDates(@Param("startTime") LocalDateTime startTime, 
                                        @Param("endTime") LocalDateTime endTime);

    @Query("SELECT e FROM ExamSchedule e ORDER BY e.examDate ASC")
    Page<ExamSchedule> findAllSortedByExamDate(Pageable pageable);
}
