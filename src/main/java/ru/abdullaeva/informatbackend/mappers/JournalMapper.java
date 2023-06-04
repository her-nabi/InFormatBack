package ru.abdullaeva.informatbackend.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.abdullaeva.informatbackend.dto.AdminJournalDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.UserJournalDto;
import ru.abdullaeva.informatbackend.dto.UserVariantDto;
import ru.abdullaeva.informatbackend.repository.UserRepository;
import ru.abdullaeva.informatbackend.repository.VariantRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JournalMapper {
    private final UserRepository userRepository;
    private final VariantRepository variantRepository;

    public UserJournalDto toUserJournalDto(UserDto user) {
        UserVariantDto userVariant = new UserVariantDto();
        userVariant.setName(user.getName());
        userVariant.setVariants(user.getVariants());
        return new UserJournalDto(userVariant);

    }

    public AdminJournalDto toAdminJournalDto(List<UserDto> users) {
        List<UserVariantDto> userVariantDtoList = new ArrayList<>();
        if (!users.isEmpty()) {
            for(UserDto userDto: users) {
                UserVariantDto userVariant = new UserVariantDto();
                userVariant.setName(userDto.getName());
                userVariant.setSurname(userDto.getSurname());
                userVariant.setVariants(userDto.getVariants());
                userVariantDtoList.add(userVariant);
            }
        }
        return new AdminJournalDto(userVariantDtoList);
    }

}
