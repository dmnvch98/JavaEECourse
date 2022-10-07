package service;

import model.User;
import repository.UserDao;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean userIsExist(final String username, final String password) {
        return userDao.userIsExist(username, password);
    }

    public void signUp(final String username, final String password) {
        userDao.save(username, password);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public List<User> filterUsers(String prefix) {
        return userDao.filterUsers(prefix);
    }
}
