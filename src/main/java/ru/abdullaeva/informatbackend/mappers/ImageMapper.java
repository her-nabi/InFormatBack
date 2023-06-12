package ru.abdullaeva.informatbackend.mappers;

import org.mapstruct.Mapper;
import ru.abdullaeva.informatbackend.dto.ImageDto;
import ru.abdullaeva.informatbackend.dto.web.WebImageDto;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageDto webImageDtoToImageDto(WebImageDto webImageDto);

}
