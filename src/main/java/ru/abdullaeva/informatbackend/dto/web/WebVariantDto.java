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
public class WebVariantDto {

    private String name;
    private List<WebTaskDto> tasks;
    private List<Integer> users;

}
