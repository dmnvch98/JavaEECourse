package org.example.repository;

import org.example.model.Friends;
import org.example.model.User;

import java.util.List;

public interface FriendDto {
    void addFriend(User firstUser, User secondUser);

    void removeFriend(Friends friends);

    List<Friends> getFriendsRecords(User firstUser, User secondUser);
}
