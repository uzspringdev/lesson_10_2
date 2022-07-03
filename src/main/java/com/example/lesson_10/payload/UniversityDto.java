package com.example.lesson_10.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDto {
    private String name;
    private String country;
    private String region;
    private String district;
    private String street;
}
