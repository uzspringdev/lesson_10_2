package com.example.lesson_10.repository;

import com.example.lesson_10.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
