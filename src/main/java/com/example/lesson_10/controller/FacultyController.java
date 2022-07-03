package com.example.lesson_10.controller;

import com.example.lesson_10.entity.Faculty;
import com.example.lesson_10.payload.FacultyDto;
import com.example.lesson_10.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
    //INJECT SERVICE
    private final FacultyService service;


    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Faculty> add(@RequestBody FacultyDto facultyDto) {
        return service.add(facultyDto);
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Faculty> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Faculty> update(@PathVariable Long id, @RequestBody FacultyDto facultyDto) {
        return service.update(id, facultyDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
