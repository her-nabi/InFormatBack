package ru.abdullaeva.informatbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abdullaeva.informatbackend.model.auth.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

    User findByNameAndSurname(String name, String surname);
}
