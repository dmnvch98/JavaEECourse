package org.example.service;

import org.example.model.FriendRequest;
import org.example.model.User;
import org.example.repository.FriendRequestDao;

import java.util.List;

public class FriendRequestService {
    private final FriendRequestDao friendRequestDao;

    public FriendRequestService(final FriendRequestDao friendRequestDao) {
        this.friendRequestDao = friendRequestDao;
    }

    public void createRequest(final User requestUser, final User approveUser) {
        friendRequestDao.createRequest(requestUser, approveUser);
    }

    public List<FriendRequest> getIncomingFriendRequests(final String username) {
        return friendRequestDao.getIncomingFriendRequests(username);
    }
}
