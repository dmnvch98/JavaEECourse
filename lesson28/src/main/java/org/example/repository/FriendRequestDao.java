package org.example.repository;

import org.example.model.FriendRequest;
import org.example.model.User;

import java.util.List;

public interface FriendRequestDao {
    void createRequest(User requestUser, User approveUser);
    void deleteRequest(FriendRequest friendRequest);

    List<FriendRequest> getIncomingFriendRequests(String username);
    FriendRequest getFriendRequest(long id);
}
