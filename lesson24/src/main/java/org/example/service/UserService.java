package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean userIsExist(final String username, final String password) {
        return userDao.isExist(username, password);
    }

    public void signUp(final String username, final String password) {
        userDao.save(username, password);
    }

    public List<User> getAllFilteredUsers(String prefix) {
        if (prefix != null) {
            return userDao.filterUsers(prefix);
        }
        else {
            return userDao.getAll();
        }
    }
}
