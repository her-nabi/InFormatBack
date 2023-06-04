package ru.abdullaeva.informatbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.mappers.VariantMapper;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.model.base.Task;
import ru.abdullaeva.informatbackend.model.base.Variant;
import ru.abdullaeva.informatbackend.repository.TaskRepository;
import ru.abdullaeva.informatbackend.repository.UserRepository;
import ru.abdullaeva.informatbackend.repository.VariantRepository;
import ru.abdullaeva.informatbackend.service.interf.VariantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {

    private final VariantRepository variantRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final VariantMapper variantMapper;

    @Override
    public boolean createVariant(Variant variant) {
        try {
            Set<Task> tasks = variant.getTasks();
            if (!tasks.isEmpty()) {
                Set<Task> newTasks = (Set<Task>)taskRepository.saveAll(tasks);
                variant.setTasks(newTasks);
                List<UserDto> users = new ArrayList<>();
                for(User u: variant.getUsers()) {
                   User user = userRepository.findById(u.getId()).orElse(null);
                   if(user != null) {
                       user.getVariants().add(variant);
                       userRepository.save(user);
                   }
                }
                variantRepository.save(variant);
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
