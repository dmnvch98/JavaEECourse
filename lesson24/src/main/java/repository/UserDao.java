package repository;

import model.User;

import java.util.List;

public interface UserDao {
    void save(String username, String password);
    void update(User user);
    void delete(int id);
    User get(int id);
    List<User> getAll();
    List<User> filterUsers(String prefix);
    boolean userIsExist(String username, String password);
}
