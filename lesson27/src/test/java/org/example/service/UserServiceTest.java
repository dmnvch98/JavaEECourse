package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.example.repository.UserRepository;
import org.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final UserDao repository = Mockito.mock(UserRepository.class);

    private final UserService sut = new UserService(repository);

    @Test
    public void shouldThrowExceptionWhenUserExists() {
        final String username = "any_name3";
        final String password = "any_password3";

        given(repository.isExist(username, password)).willReturn(true);

        final RuntimeException actual = assertThrows(
                RuntimeException.class, () -> sut.save(username, password));

        assertThat(actual)
                .hasMessage("User already exists");
    }

    @Test
    public void shouldFilterUsers() {
        final String username = "any_name";
        final String password = "any_password";

        final String username2 = "any_name2";
        final String password2 = "any_password2";

        final String filterPrefix = "any";

        final List<User> filteredUsers = sut.getAllFilteredUsers(filterPrefix);

        given(repository.filterUsers(filterPrefix)).willReturn(filteredUsers);

        final List<User> expectedUsers = Arrays.asList(new User(username, password), new User(username2, password2));

        assertThat(filteredUsers.containsAll(expectedUsers));
    }
}
