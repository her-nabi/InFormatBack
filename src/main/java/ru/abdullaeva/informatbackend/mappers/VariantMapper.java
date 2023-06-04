package ru.abdullaeva.informatbackend.mappers;

import org.mapstruct.Mapper;
import ru.abdullaeva.informatbackend.dto.VariantDto;
import ru.abdullaeva.informatbackend.model.base.Variant;

@Mapper(componentModel = "spring")
public interface VariantMapper {
    VariantDto variantToVariantDto(Variant variant);
}
