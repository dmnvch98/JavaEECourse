package org.example.repository;

import lombok.extern.log4j.Log4j2;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
public class UserRepository implements UserDao {
    private final SessionFactory sessionFactory;

    public UserRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(final String username, final String password, final String role, final Date createdAt) {
        User user = new User(username, password, role, createdAt);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExist(final String username, final String password) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.getNamedQuery("isExists")
                    .setParameter("username", username)
                    .setParameter("password", password);
            return query.getSingleResult() != null;
        } catch (Exception e) {
            if (!(e instanceof NoResultException)) {
                log.error(e);
            }
        }
        return false;
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
    @SuppressWarnings("unchecked")
    public List<User> filterUsers(final String prefix) {
        List<User> filteredUsers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query query = session.getNamedQuery("filterUsers").setParameter("prefix", prefix);
            filteredUsers = (List<User>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredUsers;
    }

    @Override
    public User getUser(final String username) {
        User user = new User();
        try (Session session = sessionFactory.openSession()) {
            Query query = session.getNamedQuery("getUser")
                    .setParameter("username", username);
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            if (!(e instanceof NoResultException)) {
                log.error(e);
            }
        }
        return user;
    }
}
