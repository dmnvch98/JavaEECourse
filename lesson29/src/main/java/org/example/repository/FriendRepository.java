package org.example.repository;

import lombok.extern.log4j.Log4j2;
import org.example.model.Friends;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Log4j2
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
    public void removeFriend(final Friends friends) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(friends);
            transaction.commit();
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Friends> getFriendsRecords(final User firstUser, final User secondUser) {
        List<Friends> friendsRecords = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getFriendsRecord")
                    .setParameter("user1", firstUser)
                    .setParameter("user2", secondUser);
            friendsRecords = (List<Friends>) query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friendsRecords;
    }
}
