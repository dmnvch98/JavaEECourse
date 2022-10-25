package org.example.repository;

import org.example.model.FriendRequest;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FriendRequestRepository implements FriendRequestDao {

    private final SessionFactory sessionFactory;

    public FriendRequestRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createRequest(final User requestUser, final User approveUser) {
        FriendRequest friendRequest = new FriendRequest(requestUser, approveUser);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(friendRequest);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRequest(final long requestId, final long removerUserId) {

    }
}
