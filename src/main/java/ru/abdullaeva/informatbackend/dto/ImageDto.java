package ru.abdullaeva.informatbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class ImageDto {

    private Integer id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private String imagePath;
    private TaskDto task;
}
