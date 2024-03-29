package ru.otus.hw_13.service;

import lombok.AllArgsConstructor;
import ru.otus.hw_13.model.User;
import ru.otus.hw_13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @____(@Autowired))
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    Environment environment;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userModel = userRepository.findByEmail(username);
        if (userModel == null) throw new UsernameNotFoundException(username);
        return new org.springframework.security.core.userdetails.User(userModel.getEmail(), userModel.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
