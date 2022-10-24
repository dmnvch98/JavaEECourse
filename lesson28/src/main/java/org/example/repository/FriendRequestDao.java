package org.example.repository;

import org.example.model.User;

public interface FriendRequestDao {
    void createRequest(User requestUser, User approveUser);
    void deleteRequest(long requestId, long removerUserId);
}
