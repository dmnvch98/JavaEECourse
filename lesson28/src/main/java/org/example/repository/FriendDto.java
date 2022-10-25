package org.example.repository;

import org.example.model.User;

import java.util.List;

public interface FriendDto {
    void addFriend(User firstUser, User secondUser);
    List<User> getUserFriends(long userId);
}
