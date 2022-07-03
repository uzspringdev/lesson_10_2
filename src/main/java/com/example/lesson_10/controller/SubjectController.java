package com.example.lesson_10.controller;

import com.example.lesson_10.entity.Subject;
import com.example.lesson_10.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    //INJECT SERVICE
    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Subject> add(@RequestBody Subject subject) {
        return service.add(subject);
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Subject> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Subject> update(@PathVariable Long id, @RequestBody Subject subject) {
        return service.update(id, subject);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
