package ru.abdullaeva.informatbackend.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.abdullaeva.informatbackend.dto.web.AdminJournalDto;
import ru.abdullaeva.informatbackend.dto.web.UserJournalDto;
import ru.abdullaeva.informatbackend.dto.web.UserVariantDto;
import ru.abdullaeva.informatbackend.dto.web.VariantJournalDto;
import ru.abdullaeva.informatbackend.model.auth.User;
import ru.abdullaeva.informatbackend.model.base.Task;
import ru.abdullaeva.informatbackend.model.base.Variant;
import ru.abdullaeva.informatbackend.repository.VariantRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JournalMapper {

    private final VariantRepository variantRepository;

    public UserJournalDto toUserJournalDto(User user, List<Variant> variants) {
        UserVariantDto userVariantDto = new UserVariantDto();
        Integer score = 0;
        List<VariantJournalDto> variantJournalDtoSet = new ArrayList<>();
        for (Variant v : variants) {
            VariantJournalDto variantJournalDto = new VariantJournalDto();
            for (Task t : v.getTasks()) {
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

    public AdminJournalDto toAdminJournalDto(List<User> users) {
        List<UserJournalDto> userJournalDtoList = new ArrayList<>();
        Integer score = 0;
        for (User u : users) {
            List<Variant> variant = variantRepository.findAllByUsersId(u.getId());
            UserJournalDto userJournalDto = toUserJournalDto(u, variant);
            userJournalDtoList.add(userJournalDto);
        }
        return new AdminJournalDto(userJournalDtoList);
    }

}
