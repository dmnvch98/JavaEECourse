package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.example.repository.UserRepository;
import org.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final UserDao repository = new UserRepository(sessionFactory);

    private final UserService sut = new UserService(repository);

    @Test
    public void shouldThrowExceptionWhenUserExists() {
        final String username = "any_name3";
        final String password = "any_password3";

        sut.signUp(username, password);

        final RuntimeException actual = assertThrows(
                RuntimeException.class, () -> sut.signUp(username, password));

        assertThat(actual)
                .hasMessage("User already exists");
    }
    @Test
    public void shouldFilterUsers() {
        final String username = "any_name";
        final String password = "any_password";

        final String username2 = "any_name2";
        final String password2 = "any_password2";

        final String username3 = "username";
        final String password3 = "password";

        final String filterPrefix = "any";

        sut.signUp(username, password);
        sut.signUp(username2, password2);
        sut.signUp(username3, password3);

        final List<User> filteredUsers = sut.getAllFilteredUsers(filterPrefix);
        final List<User> expectedUsers = Arrays.asList(new User(username, password), new User(username2, password2));

        assertThat(filteredUsers.containsAll(expectedUsers));
    }
}
