package ru.abdullaeva.informatbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.abdullaeva.informatbackend.model.auth.Role;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class UserDto {
    private Integer id;
    private Role role;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private boolean active;
    private boolean blocked;
    private Set<VariantDto> variants;
}
