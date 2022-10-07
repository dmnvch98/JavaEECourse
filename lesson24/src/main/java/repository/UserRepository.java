package repository;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepository implements UserDao {
    private final List<User> listOfUser;
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        listOfUser = getAll();
    }

    @Override
    public void save(final String username, final String password) {
        User user = new User(username, password);
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            listOfUser.add(user);
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
        return null;
    }

    @Override
    public boolean userIsExist(final String username, final String password) {
        return listOfUser
                .stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Transaction transaction = null;
        List<User> listOfUser = null;
        try (Session session = sessionFactory.openSession()) {
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

    @Override
    public List<User> filterUsers(String prefix) {
        return listOfUser.stream()
                .filter(u -> u.getUsername().startsWith(prefix)).toList();
    }
}
