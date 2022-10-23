package org.example.servlet;

import org.apache.logging.log4j.Logger;
import org.example.model.User;
import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allUsers", value = "/allusers")
public class AllUsersServlet extends HttpServlet {

    private UserService userService;
    private Logger logger;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
        logger = (Logger) config.getServletContext().getAttribute("logger");
    }
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String searchPrefix = req.getParameter("search");
        logger.info("Filter users with prefix " + searchPrefix);
        List<User> users = userService.getAllFilteredUsers(searchPrefix);
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/view/all_users.jsp").forward(req, resp);
    }
}
