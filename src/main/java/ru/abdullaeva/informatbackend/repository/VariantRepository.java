package ru.abdullaeva.informatbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abdullaeva.informatbackend.model.base.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
}
