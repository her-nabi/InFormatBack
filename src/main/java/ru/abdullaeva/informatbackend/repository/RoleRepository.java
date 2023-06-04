package ru.abdullaeva.informatbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abdullaeva.informatbackend.model.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
