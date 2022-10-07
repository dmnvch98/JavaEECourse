package listener;

import org.hibernate.SessionFactory;
import repository.UserDao;
import repository.UserRepository;
import service.UserService;
import utils.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DependencyInitializationContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(final ServletContextEvent sce) {
    try {
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      UserDao repository = new UserRepository(sessionFactory);
      UserService userService = new UserService(repository);
      sce.getServletContext().setAttribute("userService", userService);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
