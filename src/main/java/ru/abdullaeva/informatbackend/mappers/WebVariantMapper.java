package ru.abdullaeva.informatbackend.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.dto.web.WebVariantDto;
import ru.abdullaeva.informatbackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebVariantMapper {

    public VariantDto webVariantToVariantDto(WebVariantDto variant, List<TaskDto> taskDtos){
        VariantDto variantDto = new VariantDto();
        variantDto.setName(variant.getName());
        variantDto.setTasks(taskDtos);
        List<UserDto> usersDto = new ArrayList<>();
        for(Integer u: variant.getUsers()) {
            UserDto user = new UserDto();
            user.setId(u);
            usersDto.add(user);
        }
        variantDto.setUsers(usersDto);
        return  variantDto;
    }

}
