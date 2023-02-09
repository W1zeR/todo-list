package com.w1zer.service;

import com.w1zer.model.User;
import com.w1zer.repository.UserRepository;

import java.util.Objects;

public class LoginService {

    private final UserRepository userRepository = new UserRepository();

    public boolean canLogin(String login, String password) {
        User user = userRepository.getOne(login);
        return user != null && Objects.equals(user.getPassword(), password);
    }

}
