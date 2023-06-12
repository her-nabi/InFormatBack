package ru.abdullaeva.informatbackend.dto.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebTaskDto {

    private String name;
    private String type;
    private String answer;
    private String description;
    private Integer mark;
    private List<WebImageDto> images;

}
