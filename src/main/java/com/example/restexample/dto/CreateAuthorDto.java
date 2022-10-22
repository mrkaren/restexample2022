package com.example.restexample.dto;


import com.example.restexample.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorDto {

    private String name;
    private String surname;
    private String email;
    private Gender gender;
}
