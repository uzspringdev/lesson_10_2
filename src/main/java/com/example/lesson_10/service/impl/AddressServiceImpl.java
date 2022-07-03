package com.example.lesson_10.service.impl;

import com.example.lesson_10.entity.Address;
import com.example.lesson_10.repository.AddressRepository;
import com.example.lesson_10.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    //INJECT REPOSITORY
    private final AddressRepository repository;


    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Address> add(Address addressDto) {
        try {
            Address address = new Address();
            address.setCountry(addressDto.getCountry());
            address.setRegion(addressDto.getRegion());
            address.setDistrict(addressDto.getDistrict());
            address.setStreet(addressDto.getStreet());

            Address savedAddress = repository.save(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Address>> getAll() {
        try {
            List<Address> addressList = repository.findAll();
            return new ResponseEntity<>(addressList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Address> getById(Long id) {
        try {
            Optional<Address> optionalAddress = repository.findById(id);
            return optionalAddress.map(address -> new ResponseEntity<>(address, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Address> update(Long id, Address addressDto) {
        try {
            Optional<Address> optionalAddress = repository.findById(id);
            if (optionalAddress.isPresent()) {
                Address editedAddress = optionalAddress.get();
                editedAddress.setCountry(addressDto.getCountry());
                editedAddress.setRegion(addressDto.getRegion());
                editedAddress.setDistrict(addressDto.getDistrict());
                editedAddress.setStreet(addressDto.getStreet());

                Address savedAddress = repository.save(editedAddress);

                return new ResponseEntity<>(savedAddress, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return new ResponseEntity<>("Address is deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Address is not fount", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
