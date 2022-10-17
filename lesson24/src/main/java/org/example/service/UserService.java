package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isExist(final String username, final String password) {
        return userDao.isExist(username, password);
    }

    public void signUp(final String username, final String password) {
        if (isExist(username, password)) {
            throw new RuntimeException("User already exists");
        }
        userDao.save(username, password);
    }

    public List<User> getAllFilteredUsers(final String prefix) {
        if (prefix != null) {
            return userDao.filterUsers(prefix);
        } else {
            return userDao.getAll();
        }
    }

    public User getUser(final String username) {
        return userDao.getUser(username);
    }
}
