package com.example.lesson_10.service;

import com.example.lesson_10.entity.Faculty;
import com.example.lesson_10.payload.FacultyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FacultyService {

    //ADD FACULTY
    ResponseEntity<Faculty> add(FacultyDto facultyDto);

    //GET ALL FACULTY
    ResponseEntity<List<Faculty>> getAll();

    //GET FACULTY BY ID
    ResponseEntity<Faculty> getById(Long id);

    //UPDATE FACULTY
    ResponseEntity<Faculty> update(Long id,FacultyDto facultyDto);

    //DELETE FACULTY
    ResponseEntity<String> delete(Long id);

}
