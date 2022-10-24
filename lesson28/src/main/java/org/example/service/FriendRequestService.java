package org.example.service;

import org.example.model.User;
import org.example.repository.FriendRequestDao;

public class FriendRequestService {
    private final FriendRequestDao friendRequestDao;

    public FriendRequestService(final FriendRequestDao friendRequestDao) {
        this.friendRequestDao = friendRequestDao;
    }

    public void createRequest(final User requestUser, final User approveUser) {
        friendRequestDao.createRequest(requestUser, approveUser);
    }
}
