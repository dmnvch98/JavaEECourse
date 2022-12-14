package org.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.example.model.User;
import org.example.repository.UserDao;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDao repository = new UserRepository(null);
    @InjectMocks
    private UserService sut;

    @Test
    public void shouldThrowExceptionWhenUserExists() {
        final String username = "any_name3";
        final String password = "any_password3";
        final String role = "any_role";
        final Date creationDate = new Date();

        given(repository.isExist(username)).willReturn(true);
        final IOException actual = assertThrows(
                IOException.class, () -> sut.save(username, password, role, creationDate));
        assertThat(actual)
                .hasMessage("User already exists");
    }

    @Test
    public void shouldNotThrowExceptionWhenUserNotExists() {
        final String username = "any_name3";
        final String password = "any_password3";
        final String role = "any_role";
        final Date creationDate = new Date();
        given(repository.isExist(username)).willReturn(false);
        assertDoesNotThrow(() -> sut.save(username, password, role, creationDate));
    }

    @Test
    public void shouldFilterUsers() {
        final String username = "any_name3";
        final String password = "any_password3";
        final String filterPrefix = "any";
        List<User> expectedList = List.of(new User(username, password));
        given(repository.filterUsers(filterPrefix)).willReturn(expectedList);
        List<User> list = sut.getAllFilteredUsers(filterPrefix);
        assertThat(list == expectedList);
    }
}
