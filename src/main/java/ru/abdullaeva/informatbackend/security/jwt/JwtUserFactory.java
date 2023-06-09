package ru.abdullaeva.informatbackend.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.model.auth.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    /**
     * Создание пользователя для работы с Spring Security.
     * @param user - Объект класса User, используемый для создания пользователя для JWT
     * @return Jwt пользователь
     */
    public static JwtUser create(UserDto user) {
        HashSet hashSet = new HashSet<>();
        hashSet.add(user.getRole());
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                mapToGrantedAuthorities(hashSet),
                user.isBlocked()
        );
    }

    /**
     * Метод конвертирует роль User пользователя в authorities JWT пользователя
     * @param userRoles Роль пользователя User
     * @return authorities JwtUser
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
