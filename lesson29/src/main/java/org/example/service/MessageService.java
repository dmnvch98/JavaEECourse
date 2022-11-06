package org.example.service;

import org.example.model.Message;
import org.example.model.User;
import org.example.repository.message.MessageDao;

import java.util.List;

public class MessageService {
    private final MessageDao messageDao;

    public MessageService(final MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void saveMessage(final Message message) {
        messageDao.saveMessage(message);
    }

    public List<Message> getUserDialog(final User user1, final User user2) {
        return messageDao.getUserDialog(user1, user2);
    }
}
