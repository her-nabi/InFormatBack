package ru.abdullaeva.informatbackend.service.interf;

import ru.abdullaeva.informatbackend.dto.TaskDto;

public interface TaskService {

    boolean createTask(TaskDto task);

    TaskDto findTaskById(Integer id);
}
