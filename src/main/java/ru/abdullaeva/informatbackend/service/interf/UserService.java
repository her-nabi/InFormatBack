package ru.abdullaeva.informatbackend.service.interf;

import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.UserJournalDto;
import ru.abdullaeva.informatbackend.dto.web.WebUserDto;
import ru.abdullaeva.informatbackend.model.auth.User;

import java.util.List;

public interface UserService {

    UserJournalDto getUserJournal(Integer id);

    void changePassword(UserDto user, String pass);

    List<WebUserDto> getAll();

    UserDto findByLogin(String login);

    UserDto editUserInformation(User user);
}
