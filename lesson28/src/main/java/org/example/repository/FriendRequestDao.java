package org.example.repository;

import org.example.model.FriendRequest;
import org.example.model.User;

import java.util.List;

public interface FriendRequestDao {
    void createRequest(User requestUser, User approveUser);
    void deleteRequest(long requestId, long removerUserId);

    List<FriendRequest> getIncomingFriendRequests(String username);
}
