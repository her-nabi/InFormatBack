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
import ru.abdullaeva.informatbackend.mappers.UserMapper;
import ru.abdullaeva.informatbackend.mappers.VariantMapper;
import ru.abdullaeva.informatbackend.mappers.WebVariantMapper;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.model.base.Image;
import ru.abdullaeva.informatbackend.model.base.Task;
import ru.abdullaeva.informatbackend.model.base.Variant;
import ru.abdullaeva.informatbackend.repository.ImageRepository;
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
    private final VariantMapper variantMapper;
    private final WebVariantMapper webVariantMapper;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public boolean createVariant(WebVariantDto variant) {
        try {
            List<TaskDto> tasks = taskMapper.webTaskDtosToTasksDto(variant.getTasks());
            VariantDto variantDto = webVariantMapper.webVariantToVariantDto(variant, tasks);
            Variant newVariant = variantMapper.variantDtoToVariant(variantDto);
            if (!tasks.isEmpty()) {
                List<Task> newTasks = taskRepository.saveAll(taskMapper.taskDtosToTasks(tasks));
                List<User> users = new ArrayList<>();
                newVariant.setTasks(newTasks);
                newVariant.setName(variant.getName());
                for(Integer u: variant.getUsers()) {
                    User user = userRepository.findById(u).orElse(null);
                    if(user != null) {
                        user.getVariants().add(newVariant);
                        User newUser = userRepository.save(user);
                        users.add(newUser);
                    }
                }
                newVariant.setUsers(users);
                List<Variant> variants = new ArrayList<>();
                variants.add( newVariant);
                for( Task task : newTasks) {
                    task.setVariants(variants);
                }
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
    public WebVariantDto findById(Integer id) {
        Variant variant = variantRepository.findById(id).orElse(null);
        if(variant != null) {
            return webVariantMapper.variantToWebVariantDto(variant);
        }
        else {
            log.warn("In method \"findById\": variant with id {} not found", id);
        }
        return new WebVariantDto();
    }
}
