package org.example.repository;

import org.example.model.User;

import java.util.Date;
import java.util.List;

public interface UserDao {
    void save(String username, String password, String role, Date createdAt);
    List<User> getAll();
    List<User> filterUsers(String prefix);
    boolean isExist(String username, String password);
    User getUser(String username);
    List<User> getUserFriends(long userId);
}
