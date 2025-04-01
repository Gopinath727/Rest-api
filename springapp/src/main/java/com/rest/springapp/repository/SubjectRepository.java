package com.rest.springapp.repository;

import com.rest.springapp.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Subject> findByName(@Param("name") String name);

    @Query("SELECT s FROM Subject s WHERE s.code = :code")
    Optional<Subject> findByCode(@Param("code") String code);

    @Query("SELECT s FROM Subject s ORDER BY s.name ASC")
    Page<Subject> findAllSortedByName(Pageable pageable);
}
