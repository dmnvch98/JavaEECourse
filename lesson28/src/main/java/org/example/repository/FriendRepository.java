package org.example.repository;

import org.example.model.Friends;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FriendRepository implements FriendDto {

    private final SessionFactory sessionFactory;

    public FriendRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addFriend(final User firstUser, final User secondUser) {
        Friends friends = new Friends(firstUser, secondUser);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(friends);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserFriends(long userId) {
        List<User> userFriends = new ArrayList<>();
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            userFriends = session.getNamedQuery("getUserFriends").setParameter("userId", userId).getResultList();
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return userFriends;
    }
}
