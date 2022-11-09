package org.example.listener;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.repository.FriendRequestDao;
import org.example.repository.FriendRequestRepository;
import org.example.repository.UserDao;
import org.example.repository.FriendDao;
import org.example.repository.FriendRepository;
import org.example.repository.UserRepository;
import org.example.repository.message.MessageDao;
import org.example.repository.message.MessageRepository;
import org.example.service.FriendRequestService;
import org.example.service.FriendService;
import org.example.service.MessageService;
import org.hibernate.SessionFactory;
import org.example.service.UserService;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.nio.charset.StandardCharsets;

import java.security.SecureRandom;

import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;

@WebListener
public class DependencyInitializationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        UserDao repository = new UserRepository(sessionFactory);
        UserService userService = new UserService(repository);

        FriendRequestDao friendRequestDao = new FriendRequestRepository(sessionFactory);
        FriendRequestService friendRequestService = new FriendRequestService(friendRequestDao);

        MessageDao messageDto = new MessageRepository(sessionFactory);
        MessageService messageService = new MessageService(messageDto);

        FriendDao friendRepository = new FriendRepository(sessionFactory);
        FriendService friendService = new FriendService(friendRepository, messageDto);

        String secret = "SECRET";
        Hasher hasher = BCrypt.with(new SecureRandom(secret.getBytes(StandardCharsets.UTF_8)));

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("friendRequestService", friendRequestService);
        sce.getServletContext().setAttribute("friendService", friendService);
        sce.getServletContext().setAttribute("messageService", messageService);
        sce.getServletContext().setAttribute("hasher", hasher);
    }
}
