package ru.abdullaeva.informatbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abdullaeva.informatbackend.model.base.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
