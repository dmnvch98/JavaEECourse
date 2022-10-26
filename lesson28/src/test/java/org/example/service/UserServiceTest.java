package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.example.repository.UserRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Date;
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
        final String role = "any_role";
        final Date creationDate = new Date();

        given(repository.isExist(username, password)).willReturn(true);

        final IOException actual = assertThrows(
                IOException.class, () -> sut.save(username, password, role, creationDate));

        assertThat(actual)
                .hasMessage("User already exists");
    }

    @Test
    public void shouldFilterUsers() {
        final String filterPrefix = "any";

        given(repository.filterUsers(filterPrefix)).willReturn(null);

        List<User> list = sut.getAllFilteredUsers(filterPrefix);

        assertThat(list == null);
    }
}
