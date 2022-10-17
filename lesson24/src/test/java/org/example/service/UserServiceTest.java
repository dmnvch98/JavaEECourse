package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.example.repository.UserRepository;
import org.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserDao userDao;
    @Mock
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Mock
    @InjectMocks
    private UserDao repository = new UserRepository(sessionFactory);

    @InjectMocks
    private UserService sut = new UserService(repository);

//    @Test
//    public void shouldCreateUser() {
//        final String username = "username";
//        final String password = "password";
//
//        sut.signUp(username, password);
//    }
//
//    @Test
//    public void shouldReturnUser() {
//        User user = sut.getUser("username");
//        assertThat(user.getUsername()).isNotNull();
//    }
}
