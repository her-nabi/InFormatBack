package ru.abdullaeva.informatbackend.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.exception.JwtAuthenticationException;
import ru.abdullaeva.informatbackend.security.jwt.JwtUser;
import ru.abdullaeva.informatbackend.security.jwt.JwtUserFactory;
import ru.abdullaeva.informatbackend.service.interf.UserService;


@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        try {
            UserDto user = userService.findByLogin(login);
            JwtUser jwtUser = JwtUserFactory.create(user);
            log.info("In method \"loadUserByUsername\" user with login: {} successfully loaded", login);

            return jwtUser;
        } catch (Exception e) {
                throw new JwtAuthenticationException("User with login: " + login + " is banned");
        }

    }
}
