package ru.abdullaeva.informatbackend.mappers;

import org.mapstruct.Mapper;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.dto.web.WebTaskDto;
import ru.abdullaeva.informatbackend.model.base.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto taskToTaskDto(Task task);
    Task taskDtoToTask(TaskDto task);
    List<Task> taskDtosToTasks(List<TaskDto> task);
    List<Task> webTaskDtosToTasks(List<WebTaskDto> task);
    TaskDto webTaskDtoToTask(WebTaskDto task);

}
