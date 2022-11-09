package org.example.repository.message;

import org.example.model.Message;
import org.example.model.User;

import java.util.List;

public interface MessageDao {
    void saveMessage(Message message);

    List<Message> getUserMessages(User user1, User user2);

    void removeMessages(List<Message> dialog);
}
