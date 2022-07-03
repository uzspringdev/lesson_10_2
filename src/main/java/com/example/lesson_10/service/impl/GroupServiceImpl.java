package com.example.lesson_10.service.impl;

import com.example.lesson_10.entity.Faculty;
import com.example.lesson_10.entity.Group;
import com.example.lesson_10.payload.GroupDto;
import com.example.lesson_10.repository.FacultyRepository;
import com.example.lesson_10.repository.GroupRepository;
import com.example.lesson_10.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    //INJECT REPOSITORIES
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;

    public GroupServiceImpl(GroupRepository groupRepository, FacultyRepository facultyRepository) {
        this.groupRepository = groupRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public ResponseEntity<Group> add(GroupDto groupDto) {
        try {
            Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
            if (optionalFaculty.isPresent()) {
                Group group = new Group();
                group.setName(groupDto.getName());
                group.setFaculty(optionalFaculty.get());
                Group savedGroup = groupRepository.save(group);

                return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Group>> getAll() {
        try {
            List<Group> groupList = groupRepository.findAll();
            return new ResponseEntity<>(groupList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Group> getById(Long id) {
        try {
            Optional<Group> optionalGroup = groupRepository.findById(id);
            return new ResponseEntity<>(optionalGroup.orElse(null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Group> update(Long id, GroupDto groupDto) {
        try {
            Optional<Group> optionalGroup = groupRepository.findById(id);
            Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
            if (optionalGroup.isPresent() && optionalFaculty.isPresent()) {
                Faculty faculty = optionalFaculty.get();
                Group group = optionalGroup.get();

                group.setName(groupDto.getName());
                group.setFaculty(faculty);

                Group savedFaculty = groupRepository.save(group);

                return new ResponseEntity<>(savedFaculty, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            if (groupRepository.existsById(id)) {
                groupRepository.deleteById(id);
                return new ResponseEntity<>("Group is deleted", HttpStatus.OK);
            } else return new ResponseEntity<>("Group is not fount", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
