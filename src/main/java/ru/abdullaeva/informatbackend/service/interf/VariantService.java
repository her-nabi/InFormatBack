package ru.abdullaeva.informatbackend.service.interf;

import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.model.base.Variant;

public interface VariantService {

    boolean createVariant(Variant variant);

    VariantDto findById(Integer id);
}
