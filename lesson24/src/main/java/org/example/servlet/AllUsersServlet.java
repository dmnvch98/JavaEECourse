package org.example.servlet;

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

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        List<User> users;
        users = userService.getAllFilteredUsers(req.getParameter("search"));
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher(req.getContextPath() + "/view/all_users.jsp").forward(req, resp);
    }
}
