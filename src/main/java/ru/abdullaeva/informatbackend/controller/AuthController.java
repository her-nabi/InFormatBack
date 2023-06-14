package ru.abdullaeva.informatbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.abdullaeva.informatbackend.dto.web.AuthenticationRequestDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.security.jwt.JwtTokenProvider;
import ru.abdullaeva.informatbackend.service.interf.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Operation(summary = "Аутентификация пользователя" ,description = "Позволяет аутентифицировать пользователя приложения")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        Map<Object, Object> response = new HashMap<>();
        try {
            String login = requestDto.getLogin();
            UserDto user = userService.findByLogin(login);

            if (user == null) {
                throw new UsernameNotFoundException("User with login: " + login + " not found");
            }

            if(!user.isActive()) {
                userService.changePassword(user, requestDto.getPassword());
                response.put("password", "changed");
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));

            String token = jwtTokenProvider.createToken(login, user.getRole());


            response.put("login", login);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            response.put("error", "Invalid login data or user is blocked");
            return ResponseEntity.ok(response);
        }
    }
}
