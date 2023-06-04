package ru.abdullaeva.informatbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.UserJournalDto;
import ru.abdullaeva.informatbackend.dto.web.WebUserDto;
import ru.abdullaeva.informatbackend.mappers.JournalMapper;
import ru.abdullaeva.informatbackend.mappers.UserMapper;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.repository.UserRepository;
import ru.abdullaeva.informatbackend.service.interf.UserService;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JournalMapper journalMapper;
    private final PasswordEncoder passwordEncoder;

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
        return userMapper.userListToWebUserDtoList(userRepository.findAll());
    }

    @Override
    public UserDto findByLogin(String login) {
        return userMapper.userToUserDto(userRepository.findByLogin(login));
    }

    @Override
    public UserDto editUserInformation(User user) {
        return null;
    }

    private UserDto findUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return userMapper.userToUserDto(user.orElseGet(User::new));
    }

}
