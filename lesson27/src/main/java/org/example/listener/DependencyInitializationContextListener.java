package org.example.listener;

import org.example.repository.UserDao;
import org.example.repository.UserRepository;
import org.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.example.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class DependencyInitializationContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(final ServletContextEvent sce) {
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      UserDao repository = new UserRepository(sessionFactory);
      UserService userService = new UserService(repository);
      sce.getServletContext().setAttribute("userService", userService);
      Logger logger = LogManager.getLogger();
      sce.getServletContext().setAttribute("logger", logger);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
