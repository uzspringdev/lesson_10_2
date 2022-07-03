package com.example.lesson_10.service.impl;

import com.example.lesson_10.entity.Address;
import com.example.lesson_10.entity.University;
import com.example.lesson_10.payload.UniversityDto;
import com.example.lesson_10.repository.AddressRepository;
import com.example.lesson_10.repository.UniversityRepository;
import com.example.lesson_10.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    //INJECT REPOSITORIES
    private final UniversityRepository repository;
    private final AddressRepository addressRepository;

    public UniversityServiceImpl(UniversityRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
    }


    @Override
    public ResponseEntity<University> add(UniversityDto universityDto) {
        try {
            University university = new University();
            Address address = new Address();
            address.setCountry(universityDto.getCountry());
            address.setRegion(universityDto.getRegion());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            university.setName(universityDto.getName());
            Address savedAddress = addressRepository.save(address);
            university.setAddress(savedAddress);

            University savedUniversity = repository.save(university);

            return new ResponseEntity<>(savedUniversity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<University>> getAll() {
        try {
            List<University> universityList = repository.findAll();
            return new ResponseEntity<>(universityList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<University> getById(Long id) {
        try {
            Optional<University> optionalUniversity = repository.findById(id);
            return optionalUniversity.map(university -> new ResponseEntity<>(university, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<University> update(Long id, UniversityDto universityDto) {
        try {
            Optional<University> optionalUniversity = repository.findById(id);
            if (optionalUniversity.isPresent()) {
                University editedUniversity = optionalUniversity.get();
                Address editedAddress = editedUniversity.getAddress();
                editedAddress.setCountry(universityDto.getCountry());
                editedAddress.setRegion(universityDto.getRegion());
                editedAddress.setDistrict(universityDto.getDistrict());
                editedAddress.setStreet(universityDto.getStreet());
                Address savedAddress = addressRepository.save(editedAddress);
                editedUniversity.setName(universityDto.getName());
                editedUniversity.setAddress(savedAddress);

                University savedUniversity = repository.save(editedUniversity);

                return new ResponseEntity<>(savedUniversity, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            Optional<University> optionalUniversity = repository.findById(id);
            if (optionalUniversity.isPresent()) {
                repository.deleteById(id);
                addressRepository.deleteById(optionalUniversity.get().getAddress().getId());
                return new ResponseEntity<>("University is deleted", HttpStatus.OK);
            } else return new ResponseEntity<>("University is not fount", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
