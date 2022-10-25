package org.example.service;

import org.example.model.User;
import org.example.repository.FriendDto;

import java.util.List;

public class FriendService {
    private final FriendDto friendRepository;

    public FriendService(final FriendDto friendRepository) {
        this.friendRepository = friendRepository;
    }

    public void addFriend(final User firstUser, final User secondUser) {
        friendRepository.addFriend(firstUser, secondUser);
    }

}
