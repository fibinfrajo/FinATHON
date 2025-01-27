package org.project.service;

import org.project.repository.User;
import org.project.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UserLogin {

    private final UserDao userRepo;

    @Autowired
    UserLogin(UserDao userRepo) {
        this.userRepo = userRepo;
    }

    public boolean register(String username, String password ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepo.saveAndFlush(user);
        return login(username, password);
    }

    public boolean login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepo.exists(Example.of(user));

    }

    public boolean logOut(User user) {
        return true;
    }


}
