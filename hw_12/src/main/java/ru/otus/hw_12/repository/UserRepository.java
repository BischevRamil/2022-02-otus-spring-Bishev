package ru.otus.hw_12.repository;

import ru.otus.hw_12.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
