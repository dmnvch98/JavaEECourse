package org.example.repository.message;

import lombok.extern.log4j.Log4j2;
import org.example.model.Message;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MessageRepository implements MessageDao {
    private final SessionFactory sessionFactory;

    public MessageRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveMessage(Message message) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getUserDialog(User user1, User user2) {
        List<Message> userDialog = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getUserDialog")
                    .setParameter("user1", user1)
                    .setParameter("user2", user2);
            userDialog = (List<Message>) query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDialog;
    }

    @Override
    public void removeDialog(List<Message> dialog) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete Message m where m in (:dialog)").setParameter("dialog", dialog).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            log.error(e);
        }
    }
}
