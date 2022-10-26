package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isExist(final String username, final String password) {
        return userDao.isExist(username, password);
    }

    public void save(final String username, final String password,
                     final String role, final Date createdAt) throws IOException {
//        if (isExist(username, password)) {
//            throw new IOException("User already exists");
//        }
        userDao.save(username, password, role, createdAt);
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

    public List<User> getUserFriends(final long userId) {
        return userDao.getUserFriends(userId);
    }
}
