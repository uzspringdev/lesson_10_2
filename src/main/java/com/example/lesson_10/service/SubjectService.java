package com.example.lesson_10.service;

import com.example.lesson_10.entity.Subject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubjectService {

    //ADD SUBJECT
    ResponseEntity<Subject> add(Subject subjectDto);

    //GET ALL SUBJECT
    ResponseEntity<List<Subject>> getAll();

    //GET SUBJECT BY ID
    ResponseEntity<Subject> getById(Long id);

    //UPDATE SUBJECT
    ResponseEntity<Subject> update(Long id,Subject subjectDto);

    //DELETE SUBJECT
    ResponseEntity<String> delete(Long id);

}
