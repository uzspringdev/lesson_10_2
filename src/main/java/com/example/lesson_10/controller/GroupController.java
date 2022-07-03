package com.example.lesson_10.controller;

import com.example.lesson_10.entity.Group;
import com.example.lesson_10.payload.GroupDto;
import com.example.lesson_10.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/group")
public class GroupController {
    //INJECT SERVICE
    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Group> add(@RequestBody GroupDto groupDto) {
        return service.add(groupDto);
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Group> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Group> update(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        return service.update(id, groupDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
