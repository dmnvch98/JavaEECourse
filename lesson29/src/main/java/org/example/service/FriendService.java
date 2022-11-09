package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Friends;
import org.example.model.Message;
import org.example.model.User;
import org.example.repository.FriendDao;
import org.example.repository.message.MessageDao;

import java.util.List;

@RequiredArgsConstructor
public class FriendService {
    private final FriendDao friendDao;
    private final MessageDao messageDao;


    public void addFriend(final User firstUser, final User secondUser) {
        friendDao.addFriend(firstUser, secondUser);
    }

    public void removeFriend(final Friends friends) {
        friendDao.removeFriend(friends);
        List<Message> dialog = messageDao.getUserMessages(friends.getFirstUser(), friends.getSecondUser());
        messageDao.removeMessages(dialog);
    }

    public List<Friends> getFriendsRecords(final User firstUser, final User secondUser) {
        return friendDao.getFriends(firstUser, secondUser);
    }
}
