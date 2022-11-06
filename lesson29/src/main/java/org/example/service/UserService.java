package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.repository.UserDao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public boolean isExist(final String username, final String password) {
        return userDao.isExist(username, password);
    }

    public void save(final String username, final String password,
                     final String role, final Date createdAt) throws IOException {
        if (isExist(username, password)) {
            throw new IOException("User already exists");
        } else {
            userDao.save(username, password, role, createdAt);
        }
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
