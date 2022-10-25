package org.example.repository;

import org.example.model.User;

public interface FriendDto {
    void addFriend(final User firstUser, final User secondUser);
}
