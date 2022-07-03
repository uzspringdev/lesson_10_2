package com.example.lesson_10.service;

import com.example.lesson_10.entity.Group;
import com.example.lesson_10.payload.GroupDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupService {

    //ADD GROUP
    ResponseEntity<Group> add(GroupDto groupDto);

    //GET ALL GROUP
    ResponseEntity<List<Group>> getAll();

    //GET GROUP BY ID
    ResponseEntity<Group> getById(Long id);

    //UPDATE GROUP
    ResponseEntity<Group> update(Long id, GroupDto groupDto);

    //DELETE GROUP
    ResponseEntity<String> delete(Long id);

}
