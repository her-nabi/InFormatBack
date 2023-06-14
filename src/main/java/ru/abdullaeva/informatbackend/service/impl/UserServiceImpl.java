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
import ru.abdullaeva.informatbackend.mappers.VariantMapper;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.model.base.Variant;
import ru.abdullaeva.informatbackend.model.enums.RoleEnum;
import ru.abdullaeva.informatbackend.repository.UserRepository;
import ru.abdullaeva.informatbackend.repository.VariantRepository;
import ru.abdullaeva.informatbackend.service.interf.UserService;


import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JournalMapper journalMapper;
    private final PasswordEncoder passwordEncoder;
    private final VariantRepository variantRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JournalMapper journalMapper,@Lazy PasswordEncoder passwordEncoder, VariantRepository variantRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.journalMapper = journalMapper;
        this.passwordEncoder = passwordEncoder;
        this.variantRepository = variantRepository;
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
        try {
            List<Variant> variants = variantRepository.findAllByUsersId(id);
            if (!variants.isEmpty()) {
                User user = userRepository.findById(id).orElse(null);
                return journalMapper.toUserJournalDto(user, variants);
            }
        }
        catch (Exception e) {
            throw new NullPointerException("");
        }
        return new UserJournalDto();
    }

    @Override
    public List<WebUserDto> getAll() {
        List<User> users = userRepository.findAll().stream().filter(user -> user.getRole().getName().equals(RoleEnum.ROLE_USER.toString())).toList();
        return userMapper.userListToWebUserDtoList(users);
    }

    @Override
    public UserDto findByLogin(String login) {
        UserDto user = userMapper.userToUserDto(userRepository.findByLogin(login));
        if (user == null) {
            log.error("User with login: " + login + " not found");
        }
        return  user;
    }

}
