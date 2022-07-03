package com.example.lesson_10.repository;

import com.example.lesson_10.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
