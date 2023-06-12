package ru.abdullaeva.informatbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.web.UserJournalDto;
import ru.abdullaeva.informatbackend.dto.web.WebUserDto;
import ru.abdullaeva.informatbackend.mappers.JournalMapper;
import ru.abdullaeva.informatbackend.mappers.UserMapper;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.model.enums.RoleEnum;
import ru.abdullaeva.informatbackend.repository.UserRepository;
import ru.abdullaeva.informatbackend.service.interf.UserService;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JournalMapper journalMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JournalMapper journalMapper,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.journalMapper = journalMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void changePassword(UserDto user, String pass) {
        user.setPassword(passwordEncoder.encode(pass));
        user.setActive(true);
        user.setBlocked(false);
        User userUpdate = userMapper.userDtoToUser(user);
        userRepository.save(userUpdate);
    }

    @Override
    public UserJournalDto getUserJournal(Integer id) {
        UserDto user = findUserById(id);
        return journalMapper.toUserJournalDto(user);
    }

    @Override
    public List<WebUserDto> getAll() {
        List<User> users = userRepository.findAll().stream().filter(user -> user.getRole().getName().equals(RoleEnum.ROLE_USER.toString())).toList();
        return userMapper.userListToWebUserDtoList(users);
    }

    @Override
    public UserDto findByLogin(String login) {
        return userMapper.userToUserDto(userRepository.findByLogin(login));
    }

    private UserDto findUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return userMapper.userToUserDto(user.orElseGet(User::new));
    }

}
