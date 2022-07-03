package com.example.lesson_10.controller;

import com.example.lesson_10.entity.University;
import com.example.lesson_10.payload.UniversityDto;
import com.example.lesson_10.service.UniversityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/university")
public class UniversityController {
    //INJECT REPOSITORY
    private final UniversityService service;

    public UniversityController(UniversityService service) {
        this.service = service;
    }

    //ADD UNIVERSITY
    @PostMapping
    public ResponseEntity<University> add(@RequestBody UniversityDto universityDto) {
        return service.add(universityDto);
    }

    //GET UNIVERSITIES
    @GetMapping
    public ResponseEntity<List<University>> getAll() {
        return service.getAll();
    }

    //GET UNIVERSITY BY ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<University> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //UPDATE UNIVERSITY
    @PutMapping(value = "/{id}")
    public ResponseEntity<University> update(@PathVariable Long id, UniversityDto universityDto) {
        return service.update(id, universityDto);
    }

    //DELETE UNIVERSITY
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
