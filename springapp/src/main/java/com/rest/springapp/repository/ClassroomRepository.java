package com.rest.springapp.repository;

import com.rest.springapp.model.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("SELECT c FROM Classroom c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Classroom> findByName(@Param("name") String name);

    @Query("SELECT c FROM Classroom c WHERE c.capacity >= :capacity")
    List<Classroom> findByMinimumCapacity(@Param("capacity") int capacity);

    @Query("SELECT c FROM Classroom c ORDER BY c.name ASC")
    Page<Classroom> findAllSortedByName(Pageable pageable);
    l
}
