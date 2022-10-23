package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;

import java.io.IOException;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isExist(final String username, final String password) {
        return userDao.isExist(username, password);
    }

    public boolean save(final String username, final String password) throws IOException {
        if (isExist(username, password)) {
            throw new IOException("User already exists");
        }
        userDao.save(username, password);
        return true;
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
