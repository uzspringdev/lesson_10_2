package com.example.lesson_10.repository;

import com.example.lesson_10.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
