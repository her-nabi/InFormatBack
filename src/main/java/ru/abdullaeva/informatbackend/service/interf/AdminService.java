package ru.abdullaeva.informatbackend.service.interf;


import ru.abdullaeva.informatbackend.dto.AdminJournalDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.model.auth.User;

public interface AdminService {

    boolean createUser(User user);

    boolean banUser(Integer id);

    boolean resetPass(Integer id);

    boolean editUser(User user);

    AdminJournalDto getAdminJournal();
}
