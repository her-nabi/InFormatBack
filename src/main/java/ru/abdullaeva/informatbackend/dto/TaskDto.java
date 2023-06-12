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
public class TaskDto {

    private Integer id;
    private String name;
    private String type;
    private String answer;
    private String description;
    private Integer mark;
    private List<ImageDto> images;
    private List<VariantDto> variants;

}
