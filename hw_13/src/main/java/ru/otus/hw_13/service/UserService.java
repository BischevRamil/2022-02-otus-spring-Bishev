package ru.otus.hw_13.service;

import ru.otus.hw_13.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(User userDetails);
}
