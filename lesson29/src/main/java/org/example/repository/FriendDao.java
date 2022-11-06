package org.example.repository;

import org.example.model.Friends;
import org.example.model.User;

import java.util.List;

public interface FriendDao {
    void addFriend(User firstUser, User secondUser);

    void removeFriend(Friends friends);

    List<Friends> getFriends(User firstUser, User secondUser);
}
