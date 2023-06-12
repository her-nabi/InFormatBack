package ru.abdullaeva.informatbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.abdullaeva.informatbackend.dto.web.UserJournalDto;
import ru.abdullaeva.informatbackend.service.interf.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/journal/{id}")
    public UserJournalDto userJournal(@PathVariable("id") Integer id) {
        return userService.getUserJournal(id);
    }
}
