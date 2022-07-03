package com.example.lesson_10.controller;

import com.example.lesson_10.entity.Student;
import com.example.lesson_10.payload.StudentDto;
import com.example.lesson_10.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    //INJECT SERVICE
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody StudentDto studentDto) {
        return service.add(studentDto);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return service.update(id, studentDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
