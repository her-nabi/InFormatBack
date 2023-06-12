package ru.abdullaeva.informatbackend.service.interf;


import ru.abdullaeva.informatbackend.dto.web.AdminJournalDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.web.WebNewUserDto;

public interface AdminService {

    boolean createUser(WebNewUserDto user);

    boolean banUser(Integer id);

    boolean resetPass(Integer id);

    boolean editUser(UserDto user);

    AdminJournalDto getAdminJournal();
}
