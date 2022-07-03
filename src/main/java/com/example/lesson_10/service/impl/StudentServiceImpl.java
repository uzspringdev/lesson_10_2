package com.example.lesson_10.service.impl;

import com.example.lesson_10.entity.Address;
import com.example.lesson_10.entity.Group;
import com.example.lesson_10.entity.Student;
import com.example.lesson_10.entity.Subject;
import com.example.lesson_10.payload.StudentDto;
import com.example.lesson_10.repository.AddressRepository;
import com.example.lesson_10.repository.GroupRepository;
import com.example.lesson_10.repository.StudentRepository;
import com.example.lesson_10.repository.SubjectRepository;
import com.example.lesson_10.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    //INJECT REPOSITORIES
    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    public StudentServiceImpl(StudentRepository studentRepository, AddressRepository addressRepository, GroupRepository groupRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public ResponseEntity<Student> add(StudentDto studentDto) {
        try {
            Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
            Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
            List<Subject> subjectList = subjectRepository.findAllById(studentDto.getSubjectIdList());

            if (optionalAddress.isPresent() && optionalGroup.isPresent() && !subjectList.isEmpty()) {
                Address address = optionalAddress.get();
                Group group = optionalGroup.get();
                Student student = new Student();

                student.setFirstName(studentDto.getFirstName());
                student.setLastName(studentDto.getLastName());
                student.setAddress(address);
                student.setGroup(group);
                student.setSubjects(subjectList);

                Student savedStudent = studentRepository.save(student);
                return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Student>> getAll() {
        try {
            List<Student> studentList = studentRepository.findAll();
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Student> getById(Long id) {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                return new ResponseEntity<>(student, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Student> update(Long id, StudentDto studentDto) {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
            Optional<Group> optionalGroup = groupRepository.findById(id);
            List<Subject> subjectList = subjectRepository.findAllById(studentDto.getSubjectIdList());
            if (optionalStudent.isPresent() && optionalAddress.isPresent() && optionalGroup.isPresent() && !subjectList.isEmpty()) {
                Student editedStudent = optionalStudent.get();
                Address address = optionalAddress.get();
                Group group = optionalGroup.get();

                editedStudent.setFirstName(studentDto.getFirstName());
                editedStudent.setLastName(studentDto.getLastName());
                editedStudent.setAddress(address);
                editedStudent.setGroup(group);
                editedStudent.setSubjects(subjectList);

                Student savedStudent = studentRepository.save(editedStudent);

                return new ResponseEntity<>(savedStudent, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            if (studentRepository.existsById(id)) {
                studentRepository.deleteById(id);
                return new ResponseEntity<>("Student is deleted", HttpStatus.OK);
            } else return new ResponseEntity<>("Student is not fount", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
