package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.example.repository.UserRepository;
import org.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Mock
    @InjectMocks
    private UserDao repository = new UserRepository(sessionFactory);

    @InjectMocks
    private UserService sut = new UserService(repository);
//
//    @Test
//    public void shouldCreateUser() {
//        final String username = "username";
//        final String password = "password";
//
//        sut.signUp(username, password);
//    }

//    @Test
//    public void shouldThrowExceptionIfUserExists() {
//        String username = "username";
//        String password = "password";
//        final User user = mock(User.class);
//
//        //given(repository.getUser(username)).willReturn(user);
//        when(repository.getUser(username)).thenReturn(user);
//
//        RuntimeException actual = assertThrows(
//                RuntimeException.class, () -> sut.signUp(username, password));
//
//        assertThat(actual)
//                .hasMessage("User already exists");
//    }

}
