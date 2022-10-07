package repository;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class UserRepository implements Dao<User> {
    List <User> listOfUser;

    public UserRepository() {
        listOfUser = getAll();
    }

    @Override
    public void save(User value) {
        Transaction transaction;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(value);
            transaction.commit();
        }
    }

    @Override
    public void update(User value) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User get(int id) {
        for (User user : listOfUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public boolean userIsExist(final String username, final String password) {
        for (User user : listOfUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public User getUserByLoginPassword(final String username, final String password) {
        User result = new User();
        result.setId(-1);

        for (User user : listOfUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                result = user;
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List <User> getAll() {
        Transaction transaction = null;
        List <User> listOfUser = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfUser = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }
}
