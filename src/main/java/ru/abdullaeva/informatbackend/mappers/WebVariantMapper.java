package ru.abdullaeva.informatbackend.mappers;

import org.springframework.stereotype.Component;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.dto.web.WebImageDto;
import ru.abdullaeva.informatbackend.dto.web.WebTaskDto;
import ru.abdullaeva.informatbackend.dto.web.WebVariantDto;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.model.base.Image;
import ru.abdullaeva.informatbackend.model.base.Task;
import ru.abdullaeva.informatbackend.model.base.Variant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public WebVariantDto variantToWebVariantDto(Variant variant) {
        WebVariantDto webVariant = new WebVariantDto();
        webVariant.setName(variant.getName());
        List<User> users = variant.getUsers().isEmpty() ? Collections.emptyList() : variant.getUsers();
        if (!users.isEmpty()) {
            List<Integer> usersId = new ArrayList<>();
            for (User u : users) {
                usersId.add(u.getId());
            }
            webVariant.setUsers(usersId);
        }
        List<Task> tasks = variant.getTasks().isEmpty() ? Collections.emptyList() : variant.getTasks().stream().distinct().toList();
        if(!tasks.isEmpty()) {
            List<WebTaskDto> webTasks = new ArrayList<>();
            for (Task t : tasks) {
                WebTaskDto webTask = new WebTaskDto();
                List<Image> images = t.getImages().isEmpty() ? Collections.emptyList() : t.getImages();
                if (!images.isEmpty()) {
                    List<WebImageDto> webImageList = new ArrayList<>();
                    for (Image i : images) {
                        WebImageDto webImage = new WebImageDto();
                        webImage.setName(i.getName());
                        webImage.setImagePath(i.getImagePath());
                        webImage.setSize(i.getSize());
                        webImageList.add(webImage);
                    }
                    webTask.setImages(webImageList);
                }
                webTask.setName(t.getName());
                webTask.setAnswer(t.getAnswer());
                webTask.setDescription(t.getDescription());
                webTask.setMark(t.getMark());
                webTask.setType(t.getType());
                webTasks.add(webTask);
            }
            webVariant.setTasks(webTasks);
        }
            return webVariant;
    }

}
