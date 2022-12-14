package org.example.listener;

import org.example.repository.*;
import org.example.service.FriendRequestService;
import org.example.service.FriendService;
import org.hibernate.SessionFactory;
import org.example.service.UserService;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DependencyInitializationContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(final ServletContextEvent sce) {
    try {
//      Configuration configuration = new Configuration();
//      configuration.configure();
      SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
      UserDao repository = new UserRepository(sessionFactory);
      UserService userService = new UserService(repository);

      FriendRequestDao friendRequestDao = new FriendRequestRepository(sessionFactory);
      FriendRequestService friendRequestService = new FriendRequestService(friendRequestDao);

      FriendDto friendRepository = new FriendRepository(sessionFactory);
      FriendService friendService = new FriendService(friendRepository);

      sce.getServletContext().setAttribute("userService", userService);
      sce.getServletContext().setAttribute("friendRequestService", friendRequestService);
      sce.getServletContext().setAttribute("friendService", friendService);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
