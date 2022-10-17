package org.example.repository;

import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserDao {
    private final List<User> listOfUser;
    private final SessionFactory sessionFactory;

    public UserRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        listOfUser = getAll();
    }

    @Override
    public void save(final String username, final String password) {
        User user = new User(username, password);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            listOfUser.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExist(final String username, final String password) {
        return listOfUser
                .stream()
                .anyMatch(user -> user.getUsername()
                        .equals(username) && user.getPassword()
                        .equals(password));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        List<User> listOfUser = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            listOfUser = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfUser;
    }

    @Override
    public List<User> filterUsers(final String prefix) {
        return listOfUser.stream()
                .filter(u -> u.getUsername().startsWith(prefix)).toList();
    }

    @Override
    public User getUser(final String username) {
        return listOfUser
                .stream()
                .filter(u -> u.getUsername()
                        .equals(username))
                .findFirst()
                .orElse(new User());
    }

}
