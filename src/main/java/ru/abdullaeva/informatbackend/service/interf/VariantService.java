package ru.abdullaeva.informatbackend.service.interf;

import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.dto.web.WebVariantDto;

public interface VariantService {

    boolean createVariant(WebVariantDto variant);

    WebVariantDto findById(Integer id);
}
