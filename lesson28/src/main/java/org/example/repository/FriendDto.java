package org.example.repository;

import org.example.model.User;

public interface FriendDto {
    void addFriend(User firstUser, User secondUser);
}
