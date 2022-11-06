package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Message;
import org.example.model.User;
import org.example.repository.message.MessageDao;

import java.util.List;

@RequiredArgsConstructor
public class MessageService {
    private final MessageDao messageDao;

    public void saveMessage(final Message message) {
        messageDao.saveMessage(message);
    }

    public List<Message> getUserMessages(final User user1, final User user2) {
        return messageDao.getUserMessages(user1, user2);
    }
}
