package com.rest.springapp.repository;

import com.rest.springapp.model.Timetable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query("SELECT t FROM Timetable t WHERE LOWER(t.subject) LIKE LOWER(CONCAT('%', :subject, '%'))")
    List<Timetable> findBySubject(@Param("subject") String subject);

    @Query("SELECT t FROM Timetable t WHERE t.startTime BETWEEN :startTime AND :endTime ORDER BY t.startTime ASC")
    List<Timetable> findTimetablesBetweenTimes(@Param("startTime") LocalDateTime startTime, 
                                               @Param("endTime") LocalDateTime endTime);

    @Query("SELECT t FROM Timetable t ORDER BY t.startTime ASC")
    Page<Timetable> findAllSortedByStartTime(Pageable pageable);
}
