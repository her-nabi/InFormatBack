package ru.abdullaeva.informatbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.model.base.Task;

import ru.abdullaeva.informatbackend.service.interf.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskDto findTaskById(@PathVariable("id") Integer id) {
        return taskService.findTaskById(id);
    }

    @PostMapping("/create/variant")
    public boolean createVariant(@RequestBody Task task) {
        return taskService.createTask(task);
    }
}
