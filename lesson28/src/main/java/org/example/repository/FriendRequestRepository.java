package org.example.repository;

import lombok.extern.log4j.Log4j2;
import org.example.model.FriendRequest;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Log4j2
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
    @SuppressWarnings("unchecked")
    public List<FriendRequest> getOutgoingFriendRequests(String username) {
        List<FriendRequest> outgoingFriendRequests = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getOutgoingFriendRequest").setParameter("username", username);
            outgoingFriendRequests = (List<FriendRequest>) query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outgoingFriendRequests;
    }

    @Override
    public FriendRequest getFriendRequest(final long requestId) {
        FriendRequest friendRequest = new FriendRequest();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getFriendRequestById")
                    .setParameter("requestId", requestId);
            friendRequest = (FriendRequest) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (!(e instanceof NoResultException)) {
                log.error(e);
            }
        }
        return friendRequest;
    }

    @Override
    public boolean isExists(User requestUser, User approveUser) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("isFriendRequestExists")
                    .setParameter("approveUser", approveUser)
                    .setParameter("requestUser", requestUser);
            transaction.commit();
            return query.getSingleResult() != null;
        } catch (Exception e) {
            if (!(e instanceof NoResultException)) {
                log.error(e);
            }
        }
        return false;
    }

    @Override
    public void deleteRequest(final FriendRequest friendRequest) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(friendRequest);
            transaction.commit();
        } catch (Exception e) {
            log.error(e);
        }
    }
}
