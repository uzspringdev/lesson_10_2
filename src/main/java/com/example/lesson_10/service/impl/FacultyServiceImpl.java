package com.example.lesson_10.service.impl;

import com.example.lesson_10.entity.Faculty;
import com.example.lesson_10.entity.University;
import com.example.lesson_10.payload.FacultyDto;
import com.example.lesson_10.repository.FacultyRepository;
import com.example.lesson_10.repository.UniversityRepository;
import com.example.lesson_10.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FacultyServiceImpl implements FacultyService {
    //INJECT REPOSITORIES
    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.universityRepository = universityRepository;
    }


    @Override
    public ResponseEntity<Faculty> add(FacultyDto facultyDto) {
        try {
            Faculty faculty = new Faculty();
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (optionalUniversity.isPresent()) {
                faculty.setName(facultyDto.getName());
                faculty.setUniversity(optionalUniversity.get());
                Faculty savedFaculty = facultyRepository.save(faculty);
                return new ResponseEntity<>(savedFaculty, HttpStatus.CREATED);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Faculty>> getAll() {
        try {
            List<Faculty> facultyList = facultyRepository.findAll();
            return new ResponseEntity<>(facultyList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Faculty> getById(Long id) {
        try {
            Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
            return optionalFaculty.map(faculty -> new ResponseEntity<>(faculty, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Faculty> update(Long id, FacultyDto facultyDto) {
        try {
            Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (optionalFaculty.isPresent()) {
                if (!optionalUniversity.isPresent()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                Faculty editedFaculty = optionalFaculty.get();
                University university = optionalUniversity.get();
                editedFaculty.setName(facultyDto.getName());
                editedFaculty.setUniversity(university);

                Faculty savedFaculty = facultyRepository.save(editedFaculty);

                return new ResponseEntity<>(savedFaculty, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            if (facultyRepository.existsById(id)) {
                facultyRepository.deleteById(id);
                return new ResponseEntity<>("Faculty is deleted", HttpStatus.OK);
            } else return new ResponseEntity<>("Faculty is not fount", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
