package ru.abdullaeva.informatbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.abdullaeva.informatbackend.dto.web.AdminJournalDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.web.WebNewUserDto;
import ru.abdullaeva.informatbackend.mappers.JournalMapper;
import ru.abdullaeva.informatbackend.mappers.UserMapper;
import ru.abdullaeva.informatbackend.model.auth.Role;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.model.enums.RoleEnum;
import ru.abdullaeva.informatbackend.repository.UserRepository;
import ru.abdullaeva.informatbackend.service.interf.AdminService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JournalMapper journalMapper;

    private static final Role role = new Role(2, RoleEnum.ROLE_USER.name());

    public boolean createUser(WebNewUserDto user) {
        String login = user.getLogin();
        if (userRepository.findByLogin(login) != null) {
            log.error("In method \"createUser\": User with login {} already exist. ", login);
            return false;
        }
        User newUser = User.builder()
                .active(false)
                .blocked(false)
                .login(user.getLogin())
                .password((passwordEncoder.encode(user.getPassword())))
                .role(role)
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .build();
        log.info("In method \"createUser\": saving new User with login: {}", login);
        userRepository.save(newUser);
        return true;
    }

    @Override
    public boolean banUser(Integer id) {
        boolean bunUnban = false;
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setBlocked(true);
                log.info("In method \"banUser\": ban user with id = {}; login: {}", user.getId(), user.getLogin());
                bunUnban = true;
            } else {
                user.setBlocked(false);
                log.info("In method \"banUser\": unban user with id = {}; login: {}", user.getId(), user.getLogin());
            }
            userRepository.save(user);
        } else {
            log.info("In method \"banUser\": user with id {} not found", id);
            return false;
        }
        return bunUnban;
    }

    @Override
    public boolean resetPass(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode("1"));
            user.setActive(false);
            user.setBlocked(false);
            log.info("In method \"resetPass\": reset password of user with id {}", id);
            userRepository.save(user);
            return true;
        }
        log.info("In method \"resetPass\": user with id {} not found", id);
        return false;
    }

    @Override
    public boolean editUser(UserDto user) {
        if (user != null) {
            User updateUser = userRepository.findById(user.getId()).orElse(null);
            if (updateUser != null) {
                if (user.getName() != null) {
                    updateUser.setName(user.getName());
                }
                if (user.getSurname() != null) {
                    updateUser.setSurname(user.getSurname());
                }
                if (user.getLogin() != null) {
                    updateUser.setLogin(user.getLogin());
                }
                if (user.getPassword() != null) {
                    updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
                }
                if (user.getPhone() != null) {
                    updateUser.setPhone(user.getPhone());
                }
                userRepository.save(updateUser);
            }
            return true;
        }
        return false;
    }

    @Override
    public AdminJournalDto getAdminJournal() {
        List<User> user = userRepository.findAll().stream().filter(u -> u.getRole().getName().equals(RoleEnum.ROLE_USER.toString())).toList();
        return journalMapper.toAdminJournalDto(user);
    }
}
