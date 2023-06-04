package ru.abdullaeva.informatbackend.mappers;

import org.mapstruct.Mapper;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.model.base.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto taskToTaskDto(Task task);
}
