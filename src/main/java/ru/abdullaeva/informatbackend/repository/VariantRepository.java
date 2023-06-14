package ru.abdullaeva.informatbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abdullaeva.informatbackend.model.base.Variant;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
    List<Variant> findAllByUsersId(Integer id);
}
