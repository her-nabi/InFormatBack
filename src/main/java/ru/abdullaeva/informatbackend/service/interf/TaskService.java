package ru.abdullaeva.informatbackend.service.interf;

import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.model.base.Task;

public interface TaskService {

    boolean createTask(Task task);

    TaskDto findTaskById(Integer id);
}
