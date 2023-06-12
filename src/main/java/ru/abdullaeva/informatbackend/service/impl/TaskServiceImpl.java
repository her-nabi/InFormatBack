package ru.abdullaeva.informatbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.mappers.TaskMapper;
import ru.abdullaeva.informatbackend.model.base.Task;

import ru.abdullaeva.informatbackend.repository.TaskRepository;
import ru.abdullaeva.informatbackend.service.interf.TaskService;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public boolean createTask(TaskDto task) {
        if(task == null) {
            log.warn("In method \"createTask\": Task is empty!");
            return false;
        }
        try {
            taskRepository.save(taskMapper.taskDtoToTask(task));
            return true;
        }
        catch (Exception e ) {
            log.error("In method \"createTask\": " + e);
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override
    public TaskDto findTaskById(Integer id) {
        Task task = taskRepository.findById(id).orElse(null);
        if(task != null) {
            return taskMapper.taskToTaskDto(task);
        }
        else {
            log.warn("In method \"findTaskById\": task with id {} not found", id);
        }
        return new TaskDto();
    }
}
