package com.example.lesson_10.repository;

import com.example.lesson_10.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> getAllByGroupFacultyUniversityId(Long universityId);
}
