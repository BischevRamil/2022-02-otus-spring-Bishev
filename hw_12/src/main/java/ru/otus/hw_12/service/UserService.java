package ru.otus.hw_12.service;

import ru.otus.hw_12.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(User userDetails);
}
