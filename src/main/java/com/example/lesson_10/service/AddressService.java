package com.example.lesson_10.service;

import com.example.lesson_10.entity.Address;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {

    //ADD ADDRESS
    ResponseEntity<Address> add(Address address);

    //GET ALL ADDRESS
    ResponseEntity<List<Address>> getAll();

    //GET ADDRESS BY ID
    ResponseEntity<Address> getById(Long id);

    //UPDATE ADDRESS
    ResponseEntity<Address> update(Long id,Address address);

    //DELETE ADDRESS
    ResponseEntity<String> delete(Long id);

}
