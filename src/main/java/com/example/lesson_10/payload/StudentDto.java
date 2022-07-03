package com.example.lesson_10.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String firstName;
    private String lastName;
    private Long addressId;
    private Long groupId;
    private List<Long> subjectIdList;
}
