package service;

import model.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signIn(final String username, final String password) {
        return userRepository.userIsExist(username, password);
    }
}
