package ru.abdullaeva.informatbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class VariantDto {
    private Integer id;
    private String name;
    private List<TaskDto> tasks;
    private List<UserDto> users;
}
