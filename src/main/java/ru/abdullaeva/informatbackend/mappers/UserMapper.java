package ru.abdullaeva.informatbackend.mappers;

import org.mapstruct.Mapper;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.web.WebUserDto;
import ru.abdullaeva.informatbackend.model.auth.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    List<UserDto> userListToUserDtoList(List<User> users);
    List<WebUserDto> userListToWebUserDtoList(List<User> users);
}
