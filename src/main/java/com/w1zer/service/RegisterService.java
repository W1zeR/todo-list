package com.w1zer.service;

import com.w1zer.model.User;
import com.w1zer.repository.UserRepository;

public class RegisterService {
    private final UserRepository userRepository = new UserRepository();

    public boolean canRegister(String login) {
        User user = userRepository.getOne(login);
        return user == null;
    }

    public void register(String login, String password) {
        userRepository.insert(login, password);
    }

}
