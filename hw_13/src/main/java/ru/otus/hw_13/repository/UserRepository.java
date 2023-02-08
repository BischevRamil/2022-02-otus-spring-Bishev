package ru.otus.hw_13.repository;

import ru.otus.hw_13.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
