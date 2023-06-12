package ru.abdullaeva.informatbackend.mappers;

import org.springframework.stereotype.Component;
import ru.abdullaeva.informatbackend.dto.web.AdminJournalDto;
import ru.abdullaeva.informatbackend.dto.TaskDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.web.UserJournalDto;
import ru.abdullaeva.informatbackend.dto.web.UserVariantDto;
import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.dto.web.VariantJournalDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class JournalMapper {

    public UserJournalDto toUserJournalDto(UserDto user) {
        UserVariantDto userVariantDto = new UserVariantDto();
        Integer score = 0;
        List<VariantJournalDto> variantJournalDtoSet = new ArrayList<>();
        for (VariantDto v : user.getVariants()) {
            VariantJournalDto variantJournalDto = new VariantJournalDto();
            for (TaskDto t : v.getTasks()) {
                score += t.getMark();
            }
            variantJournalDto.setName(v.getName());
            variantJournalDto.setResult(score);
            variantJournalDtoSet.add(variantJournalDto);
            score = 0;
        }
        userVariantDto.setVariants(variantJournalDtoSet);
        userVariantDto.setName(user.getName());
        userVariantDto.setSurname(user.getSurname());
        return new UserJournalDto(userVariantDto);

    }

    public AdminJournalDto toAdminJournalDto(List<UserDto> users) {
        List<UserVariantDto> userVariantDtoList = new ArrayList<>();
        Integer score = 0;
        for (UserDto u : users) {
            UserVariantDto userVariantDto = new UserVariantDto();
            List<VariantJournalDto> variantJournalDtoSet = new ArrayList<>();
            for (VariantDto v : u.getVariants()) {
                VariantJournalDto variantJournalDto = new VariantJournalDto();
                for (TaskDto t : v.getTasks()) {
                    score += t.getMark();
                }
                variantJournalDto.setName(v.getName());
                variantJournalDto.setResult(score);
                variantJournalDtoSet.add(variantJournalDto);
                score = 0;
            }
            userVariantDto.setVariants(variantJournalDtoSet);
            userVariantDto.setName(u.getName());
            userVariantDto.setSurname(u.getSurname());
        }
        return new AdminJournalDto(userVariantDtoList);
    }

}
