package org.example.servlet;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class AllUsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String searchPrefix = req.getParameter("search");
        log.info("Filter users with prefix " + searchPrefix);
        List<User> users = userService.getAllFilteredUsers(searchPrefix);
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/view/all_users.jsp").forward(req, resp);
    }
}
