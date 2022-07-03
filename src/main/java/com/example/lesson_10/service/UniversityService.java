package com.example.lesson_10.service;

import com.example.lesson_10.entity.University;
import com.example.lesson_10.payload.UniversityDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UniversityService {

    //ADD UNIVERSITY
    ResponseEntity<University> add(UniversityDto universityDto);

    //GET ALL UNIVERSITY
    ResponseEntity<List<University>> getAll();

    //GET UNIVERSITY BY ID
    ResponseEntity<University> getById(Long id);

    //UPDATE UNIVERSITY
    ResponseEntity<University> update(Long id,UniversityDto universityDto);

    //DELETE UNIVERSITY
    ResponseEntity<String> delete(Long id);

}
