package com.example.lesson_10.service;

import com.example.lesson_10.entity.Student;
import com.example.lesson_10.payload.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    //ADD STUDENT
    ResponseEntity<Student> add(StudentDto studentDto);

    //GET ALL STUDENT
    ResponseEntity<List<Student>> getAll();

    //GET STUDENT BY ID
    ResponseEntity<Student> getById(Long id);

    //UPDATE STUDENT
    ResponseEntity<Student> update(Long id,StudentDto studentDto);

    //DELETE STUDENT
    ResponseEntity<String> delete(Long id);

}
