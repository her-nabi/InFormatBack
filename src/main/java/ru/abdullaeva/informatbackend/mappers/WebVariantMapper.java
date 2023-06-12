package ru.abdullaeva.informatbackend.mappers;

import org.springframework.stereotype.Component;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.dto.web.WebVariantDto;

import java.util.List;

@Component
public class WebVariantMapper {

    public VariantDto webVariantToVariantDto(WebVariantDto variant, List<TaskDto> taskDtos){
        VariantDto variantDto = new VariantDto();
        variantDto.setName(variant.getName());
        variantDto.setTasks(taskDtos);
        return  variantDto;
    }
}
