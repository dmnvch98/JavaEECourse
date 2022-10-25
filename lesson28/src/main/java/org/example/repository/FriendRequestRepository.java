package org.example.repository;

import org.example.model.FriendRequest;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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
    @SuppressWarnings("unchecked")
    public List<FriendRequest> getIncomingFriendRequests(final String username) {
        List<FriendRequest> incomingFriendRequests = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getIncomingFriendRequests").setParameter("username", username);
            incomingFriendRequests = (List<FriendRequest>) query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomingFriendRequests;
    }

    @Override
    public void deleteRequest(final long requestId, final long removerUserId) {

    }
}
