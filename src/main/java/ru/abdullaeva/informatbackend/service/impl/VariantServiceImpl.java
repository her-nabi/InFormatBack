package ru.abdullaeva.informatbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.abdullaeva.informatbackend.dto.ImageDto;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.dto.web.WebImageDto;
import ru.abdullaeva.informatbackend.dto.web.WebTaskDto;
import ru.abdullaeva.informatbackend.dto.web.WebVariantDto;
import ru.abdullaeva.informatbackend.mappers.ImageMapper;
import ru.abdullaeva.informatbackend.mappers.TaskMapper;
import ru.abdullaeva.informatbackend.mappers.VariantMapper;
import ru.abdullaeva.informatbackend.mappers.WebVariantMapper;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.model.base.Image;
import ru.abdullaeva.informatbackend.model.base.Task;
import ru.abdullaeva.informatbackend.model.base.Variant;
import ru.abdullaeva.informatbackend.repository.TaskRepository;
import ru.abdullaeva.informatbackend.repository.UserRepository;
import ru.abdullaeva.informatbackend.repository.VariantRepository;
import ru.abdullaeva.informatbackend.service.interf.VariantService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {

    private final VariantRepository variantRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final VariantMapper variantMapper;
    private final TaskMapper taskMapper;
    private final ImageMapper imageMapper;
    private final WebVariantMapper webVariantMapper;

    @Override
    public boolean createVariant(WebVariantDto variant) {
        try {
            Variant newVariant = new Variant();
            List<WebTaskDto> tasks = variant.getTasks();
            if (!tasks.isEmpty()) {
                List<TaskDto> taskDtos = new ArrayList<>();
                for(WebTaskDto webTaskDto : tasks) {
                    TaskDto taskDto = taskMapper.webTaskDtoToTask(webTaskDto);
//                    List<ImageDto> imageDtos = new ArrayList<>();
//                    for (WebImageDto webImageDto : webTaskDto.getImages()) {
//                        ImageDto imageDto = imageMapper.webImageDtoToImageDto(webImageDto);
//                        imageDtos.add(imageDto);
//                    }
                    taskDtos.add(taskDto);
                }
                List<VariantDto> variantDtos = new ArrayList<>();
                VariantDto variantDto = webVariantMapper.webVariantToVariantDto(variant, taskDtos);
                variantDtos.add(variantDto);
                for (TaskDto taskDto: taskDtos) {
                    taskDto.setVariants(variantDtos);
                }
                List<Task> newTasks = taskRepository.saveAll(taskMapper.taskDtosToTasks(taskDtos));
                newVariant.setTasks(newTasks);
                newVariant.setName(variant.getName());
                List<User> users = new ArrayList<>();
                for(Integer u: variant.getUsers()) {
                   User user = userRepository.findById(u).orElse(null);
                   if(user != null) {
                       user.getVariants().add(newVariant);
                       User newUser = userRepository.save(user);
                       users.add(newUser);
                   }
                }
                newVariant.setUsers(users);
                variantRepository.save(newVariant);
                return true;
            }
        }
        catch (NullPointerException e) {
            log.error(e + "In method \" createVariant\" Variant is empty! ");
            throw new NullPointerException(e.getMessage());
        }
        return false;
    }

    @Override
    public VariantDto findById(Integer id) {
        Variant variant = variantRepository.findById(id).orElse(null);
        if(variant != null) {
            return variantMapper.variantToVariantDto(variant);
        }
        else {
            log.warn("In method \"findById\": variant with id {} not found", id);
        }
        return new VariantDto();
    }
}
