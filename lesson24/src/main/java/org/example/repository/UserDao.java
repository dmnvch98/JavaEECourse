package org.example.repository;

import org.example.model.User;

import java.util.List;

public interface UserDao {
    void save(String username, String password);
    List<User> getAll();
    List<User> filterUsers(String prefix);
    boolean isExist(String username, String password);
}
