package com.example.lesson_10.service.impl;

import com.example.lesson_10.entity.Subject;
import com.example.lesson_10.repository.SubjectRepository;
import com.example.lesson_10.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    //INJECT REPOSITORY
    private final SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Subject> add(Subject subjectDto) {
        try {
            Subject subject = new Subject();
            subject.setName(subjectDto.getName());
            Subject savedSubject = repository.save(subject);
            return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Subject>> getAll() {
        try {
            List<Subject> subjectList = repository.findAll();
            return new ResponseEntity<>(subjectList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Subject> getById(Long id) {
        try {
            Optional<Subject> optionalSubject = repository.findById(id);
            if (optionalSubject.isPresent()) {
                Subject subject = optionalSubject.get();
                return new ResponseEntity<>(subject, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Subject> update(Long id, Subject subjectDto) {
        try {
            Optional<Subject> optionalSubject = repository.findById(id);
            if (optionalSubject.isPresent()) {
                Subject editedSubject = optionalSubject.get();
                editedSubject.setName(subjectDto.getName());
                Subject savedSubject = repository.save(editedSubject);
                return new ResponseEntity<>(savedSubject, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            Optional<Subject> optionalSubject = repository.findById(id);
            if (optionalSubject.isPresent()) {
                repository.deleteById(id);
                return new ResponseEntity<>("Subject is deleted", HttpStatus.OK);
            } else return new ResponseEntity<>("Subject is not fount", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
