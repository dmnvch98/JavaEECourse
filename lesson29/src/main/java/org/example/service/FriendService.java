package org.example.service;

import org.example.model.Friends;
import org.example.model.Message;
import org.example.model.User;
import org.example.repository.FriendDto;
import org.example.repository.message.MessageDao;

import java.util.List;

public class FriendService {
    private final FriendDto friendDto;
    private final MessageDao messageDao;

    public FriendService(final FriendDto friendDto, final MessageDao messageDao) {
        this.friendDto = friendDto;
        this.messageDao = messageDao;
    }

    public void addFriend(final User firstUser, final User secondUser) {
        friendDto.addFriend(firstUser, secondUser);
    }

    public void removeFriend(final Friends friends) {
        friendDto.removeFriend(friends);
        List<Message> dialog = messageDao.getUserDialog(friends.getFirstUser(), friends.getSecondUser());
        messageDao.removeDialog(dialog);
    }

    public List<Friends> getFriendsRecords(final User firstUser, final User secondUser) {
        return friendDto.getFriendsRecords(firstUser, secondUser);
    }
}
