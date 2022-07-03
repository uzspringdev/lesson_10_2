package com.example.lesson_10.repository;

import com.example.lesson_10.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
